package eth.nooobcoder.jugtours.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import eth.nooobcoder.jugtours.model.Group;

// Tests for GroupController

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupControllerTest {

  @Autowired
  private GroupController controller;

  @Test
  public void testGetGroups() {
    assertNotNull(controller.groups());
  }

  @Test
  public void testGetGroup() {
    assertNotNull(controller.getGroup(1L));
  }

  @Test
  public void testCreateGroup() throws URISyntaxException {
    assertNotNull(controller.createGroup(new Group()));
  }

  @Test
  public void testUpdateGroup() {
    assertNotNull(controller.updateGroup(new Group()));
  }

  @Test
  public void testDeleteGroup() {
    assertNotNull(controller.deleteGroup(1L));
  }

  @Test
  public void testDeleteGroupNotFound() {
    assertNotNull(controller.deleteGroup(2L));
  }
}
