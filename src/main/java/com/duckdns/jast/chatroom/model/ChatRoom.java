package com.duckdns.jast.chatroom.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Validated
public class ChatRoom   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("created")
  private Date created;

  public ChatRoom id(Integer id) {
    this.id = id;
    return this;
  }

  @ApiModelProperty(readOnly = true, value = "Chat room identifier")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ChatRoom name(String name) {
    this.name = name;
    return this;
  }

  @ApiModelProperty(required = true, value = "Chat room name")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ChatRoom description(String description) {
    this.description = description;
    return this;
  }

  @ApiModelProperty(required = true, value = "Chat room description")
  @NotNull
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ChatRoom created(Date created) {
    this.created = created;
    return this;
  }

  @ApiModelProperty(readOnly = true, value = "Chat room creation date")
  @Valid
  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

}

