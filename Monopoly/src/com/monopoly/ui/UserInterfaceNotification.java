package com.monopoly.ui;

import java.util.Map;

public class UserInterfaceNotification {
  public final UserInterfaceEvents event;
  public final Map<String, Object> args;
  public UserInterfaceNotification(UserInterfaceEvents event, Map<String, Object> args){
    this.event = event;
    this.args = args;
  }
}
