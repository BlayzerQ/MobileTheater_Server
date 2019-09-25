package com.forgegrid.controllers.rest;

import com.forgegrid.bussines.service.StorageService;
import com.forgegrid.bussines.service.impl.FileSystemStorageService;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.nio.file.FileSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserFilesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private StorageService storageService;

    @TestConfiguration
    static class InMemoryFileSystemConfiguration {

        @Value("${front.upload-dir}")
        private String userFilesUploadDirectory;

        @Bean
        @Primary
        public StorageService inMemoryFileSystemStorageService() throws IOException {
            FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
            return new FileSystemStorageService(fs.getPath(userFilesUploadDirectory));
        }
    }

    @Test
    @WithMockUser
    public void uploadFileStatusCodeTest() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "foo".getBytes());
        mockMvc.perform(multipart("/rest/file/upload").file(multipartFile).with(csrf()))
                .andExpect(status().isOk());
        then(storageService).should().saveFileForUsername(multipartFile, "user");
    }

    @Test
    public void uploadFileUnauthorizedStatusCodeTest() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "foo".getBytes());
        mockMvc.perform(multipart("/rest/file/upload").file(multipartFile).with(csrf()))
                .andExpect(status().isFound());
        then(storageService).shouldHaveZeroInteractions();
    }

    @Test
    @WithMockUser
    public void uploadFileWithInvalidNameTest() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "...txt", "text/plain", "foo".getBytes());
        MvcResult response = mockMvc.perform(multipart("/rest/file/upload").file(multipartFile).with(csrf()))
                .andExpect(status().isBadRequest()).andReturn();
        assertEquals(response.getResponse().getContentAsString(), "Invalid file!\n");
        then(storageService).shouldHaveZeroInteractions();
    }

    @Test
    @WithMockUser
    public void uploadFileWithNullOriginalNameTest() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", null, "text/plain", "foo".getBytes());
        MvcResult response = mockMvc.perform(multipart("/rest/file/upload").file(multipartFile).with(csrf()))
                .andExpect(status().isBadRequest()).andReturn();
        assertEquals(response.getResponse().getContentAsString(), "Invalid file!\n");
        then(storageService).shouldHaveZeroInteractions();
    }

    @Test
    @WithMockUser
    public void uploadFileWithInvalidSymbolInNameTest() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "foo?bar.txt", "text/plain", "foo".getBytes());
        MvcResult response = mockMvc.perform(multipart("/rest/file/upload").file(multipartFile).with(csrf()))
                .andExpect(status().isBadRequest()).andReturn();
        assertEquals(response.getResponse().getContentAsString(), "Invalid file!\n");
        then(storageService).shouldHaveZeroInteractions();
    }

    @Test
    @WithMockUser
    public void uploadAndRetrieveFileTest() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "foo".getBytes());
        mockMvc.perform(multipart("/rest/file/upload").file(multipartFile).with(csrf()))
                .andExpect(status().isOk());
        then(storageService).should().saveFileForUsername(multipartFile, "user");
        MvcResult downloadRequestResult = mockMvc.perform(get("/rest/file/retrieve/test.txt"))
                .andExpect(status().isOk()).andReturn();
        assertEquals(downloadRequestResult.getResponse().getContentType(), "text/plain");
        assertEquals(downloadRequestResult.getResponse().getContentAsString(), "foo");
        then(storageService).should().retrieveFileForUsername("test.txt", "user");
    }

    @Test
    public void retrieveFileUnauthorizedTest() throws Exception {
        MvcResult downloadRequestResult = mockMvc.perform(get("/rest/file/retrieve/test.txt"))
                .andExpect(status().isFound()).andReturn();
        assertNull(downloadRequestResult.getResponse().getContentType());
        assertTrue(downloadRequestResult.getResponse().getContentAsString().isEmpty());
        then(storageService).shouldHaveZeroInteractions();
    }

    @Test
    @WithMockUser
    public void retrieveNonExistentFileTest() throws Exception {
        MvcResult downloadRequestResult = mockMvc.perform(get("/rest/file/retrieve/test.txt"))
                .andExpect(status().isNotFound()).andReturn();
        assertNull(downloadRequestResult.getResponse().getContentType());
        assertTrue(downloadRequestResult.getResponse().getContentAsString().isEmpty());
        then(storageService).should().retrieveFileForUsername("test.txt", "user");
    }

    @Test
    @WithMockUser
    public void retrieveFileWithNoExtensionTest() throws Exception {
        MvcResult downloadRequestResult = mockMvc.perform(get("/rest/file/retrieve/test"))
                .andExpect(status().isNotFound()).andReturn();
        assertNull(downloadRequestResult.getResponse().getContentType());
        assertTrue(downloadRequestResult.getResponse().getContentAsString().isEmpty());
        then(storageService).should().retrieveFileForUsername("test", "user");
    }

    @Test
    @WithMockUser
    public void retrieveFileWithInvalidNameTest() throws Exception {
        MvcResult downloadRequestResult = mockMvc.perform(get("/rest/file/retrieve/...txt"))
                .andExpect(status().isBadRequest()).andReturn();
        assertEquals(downloadRequestResult.getResponse().getContentType(), "text/plain;charset=ISO-8859-1");
        assertEquals(downloadRequestResult.getResponse().getContentAsString(), "Invalid file name!\n");
        then(storageService).shouldHaveZeroInteractions();
    }

    @Test
    @WithMockUser
    public void retrieveFileWithInvalidSymbolInNameTest() throws Exception {
        MvcResult downloadRequestResult = mockMvc.perform(get("/rest/file/retrieve/foo`bar.txt"))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(downloadRequestResult.getResponse().getContentType() != null &&
                downloadRequestResult.getResponse().getContentType().startsWith("text/plain"));
        assertEquals(downloadRequestResult.getResponse().getContentAsString(), "Invalid file name!\n");
        then(storageService).shouldHaveZeroInteractions();
    }
}
