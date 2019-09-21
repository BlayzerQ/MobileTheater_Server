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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.FileSystem;

import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FileUploadControllerTest {

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
        public StorageService setupInMemoryFileSystemForStorageService() {
            FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
            return new FileSystemStorageService(fs.getPath(userFilesUploadDirectory));
        }
    }

    @Test
    public void uploadFileStatusCodeTest() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "foo".getBytes());
        doNothing().when(storageService).saveFile(multipartFile);
        mockMvc.perform(multipart("/rest/file/upload").file(multipartFile).with(csrf()))
                .andExpect(status().isOk());
    }
}
