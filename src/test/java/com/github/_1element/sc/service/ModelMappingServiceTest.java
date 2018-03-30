package com.github._1element.sc.service;

import com.github._1element.sc.SurveillanceCenterApplication;
import com.github._1element.sc.domain.Camera;
import com.github._1element.sc.dto.CameraResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SurveillanceCenterApplication.class)
@WebAppConfiguration
public class ModelMappingServiceTest {

  @Autowired
  private ModelMappingService modelMappingService;

  @Test
  public void testConvertCameraToResource() throws Exception {
    // arrange
    Camera camera = new Camera("front", "Camera Name", "internal-host.example", "ftpUser",
        "ftpPass", "/ftp/dir", "http://internal.example/snapshot.cgi", true, true);

    // act
    CameraResource cameraResource = modelMappingService.convertCameraToResource(camera);

    // assert
    assertEquals("front", cameraResource.getId());
    assertEquals("Camera Name", cameraResource.getName());
    assertEquals("http://localhost/proxy/snapshot/front", cameraResource.getSnapshotProxyUrl());
    assertEquals("http://localhost/generate/mjpeg/front", cameraResource.getStreamGeneratorUrl());
  }

}