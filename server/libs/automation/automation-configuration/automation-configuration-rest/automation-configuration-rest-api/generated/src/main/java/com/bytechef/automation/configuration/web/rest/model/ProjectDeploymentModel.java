package com.bytechef.automation.configuration.web.rest.model;

import java.net.URI;
import java.util.Objects;
import com.bytechef.automation.configuration.web.rest.model.EnvironmentModel;
import com.bytechef.automation.configuration.web.rest.model.ProjectDeploymentWorkflowModel;
import com.bytechef.automation.configuration.web.rest.model.TagModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Contains configurations and connections required for the execution of project workflows.
 */

@Schema(name = "ProjectDeployment", description = "Contains configurations and connections required for the execution of project workflows.")
@JsonTypeName("ProjectDeployment")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-15T08:55:37.379478+02:00[Europe/Zagreb]", comments = "Generator version: 7.13.0")
public class ProjectDeploymentModel {

  private @Nullable String createdBy;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime createdDate;

  private @Nullable String description;

  private @Nullable Boolean enabled;

  private @Nullable EnvironmentModel environment;

  private @Nullable Long id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime lastExecutionDate;

  private @Nullable String lastModifiedBy;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime lastModifiedDate;

  private String name;

  private @Nullable Long projectId;

  private @Nullable Integer projectVersion;

  private @Nullable Object project;

  @Valid
  private List<@Valid ProjectDeploymentWorkflowModel> projectDeploymentWorkflows = new ArrayList<>();

  @Valid
  private List<@Valid TagModel> tags = new ArrayList<>();

  private @Nullable Integer version;

  public ProjectDeploymentModel() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProjectDeploymentModel(String name) {
    this.name = name;
  }

  public ProjectDeploymentModel createdBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  /**
   * The created by.
   * @return createdBy
   */
  
  @Schema(name = "createdBy", accessMode = Schema.AccessMode.READ_ONLY, description = "The created by.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdBy")
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public ProjectDeploymentModel createdDate(OffsetDateTime createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  /**
   * The created date.
   * @return createdDate
   */
  @Valid 
  @Schema(name = "createdDate", accessMode = Schema.AccessMode.READ_ONLY, description = "The created date.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdDate")
  public OffsetDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(OffsetDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public ProjectDeploymentModel description(String description) {
    this.description = description;
    return this;
  }

  /**
   * The description of a project deployment.
   * @return description
   */
  
  @Schema(name = "description", description = "The description of a project deployment.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProjectDeploymentModel enabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  /**
   * If a project deployment is enabled or not.
   * @return enabled
   */
  
  @Schema(name = "enabled", description = "If a project deployment is enabled or not.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("enabled")
  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public ProjectDeploymentModel environment(EnvironmentModel environment) {
    this.environment = environment;
    return this;
  }

  /**
   * Get environment
   * @return environment
   */
  @Valid 
  @Schema(name = "environment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("environment")
  public EnvironmentModel getEnvironment() {
    return environment;
  }

  public void setEnvironment(EnvironmentModel environment) {
    this.environment = environment;
  }

  public ProjectDeploymentModel id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * The id of a project deployment.
   * @return id
   */
  
  @Schema(name = "id", accessMode = Schema.AccessMode.READ_ONLY, description = "The id of a project deployment.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProjectDeploymentModel lastExecutionDate(OffsetDateTime lastExecutionDate) {
    this.lastExecutionDate = lastExecutionDate;
    return this;
  }

  /**
   * The last execution date.
   * @return lastExecutionDate
   */
  @Valid 
  @Schema(name = "lastExecutionDate", accessMode = Schema.AccessMode.READ_ONLY, description = "The last execution date.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastExecutionDate")
  public OffsetDateTime getLastExecutionDate() {
    return lastExecutionDate;
  }

  public void setLastExecutionDate(OffsetDateTime lastExecutionDate) {
    this.lastExecutionDate = lastExecutionDate;
  }

  public ProjectDeploymentModel lastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
    return this;
  }

  /**
   * The last modified by.
   * @return lastModifiedBy
   */
  
  @Schema(name = "lastModifiedBy", accessMode = Schema.AccessMode.READ_ONLY, description = "The last modified by.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastModifiedBy")
  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public ProjectDeploymentModel lastModifiedDate(OffsetDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
    return this;
  }

  /**
   * The last modified date.
   * @return lastModifiedDate
   */
  @Valid 
  @Schema(name = "lastModifiedDate", accessMode = Schema.AccessMode.READ_ONLY, description = "The last modified date.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastModifiedDate")
  public OffsetDateTime getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(OffsetDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public ProjectDeploymentModel name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of a project deployment.
   * @return name
   */
  @NotNull 
  @Schema(name = "name", description = "The name of a project deployment.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProjectDeploymentModel projectId(Long projectId) {
    this.projectId = projectId;
    return this;
  }

  /**
   * The id of a project.
   * @return projectId
   */
  
  @Schema(name = "projectId", description = "The id of a project.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("projectId")
  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  public ProjectDeploymentModel projectVersion(Integer projectVersion) {
    this.projectVersion = projectVersion;
    return this;
  }

  /**
   * The version of a project.
   * @return projectVersion
   */
  
  @Schema(name = "projectVersion", description = "The version of a project.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("projectVersion")
  public Integer getProjectVersion() {
    return projectVersion;
  }

  public void setProjectVersion(Integer projectVersion) {
    this.projectVersion = projectVersion;
  }

  public ProjectDeploymentModel project(Object project) {
    this.project = project;
    return this;
  }

  /**
   * Get project
   * @return project
   */
  
  @Schema(name = "project", accessMode = Schema.AccessMode.READ_ONLY, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("project")
  public Object getProject() {
    return project;
  }

  public void setProject(Object project) {
    this.project = project;
  }

  public ProjectDeploymentModel projectDeploymentWorkflows(List<@Valid ProjectDeploymentWorkflowModel> projectDeploymentWorkflows) {
    this.projectDeploymentWorkflows = projectDeploymentWorkflows;
    return this;
  }

  public ProjectDeploymentModel addProjectDeploymentWorkflowsItem(ProjectDeploymentWorkflowModel projectDeploymentWorkflowsItem) {
    if (this.projectDeploymentWorkflows == null) {
      this.projectDeploymentWorkflows = new ArrayList<>();
    }
    this.projectDeploymentWorkflows.add(projectDeploymentWorkflowsItem);
    return this;
  }

  /**
   * Get projectDeploymentWorkflows
   * @return projectDeploymentWorkflows
   */
  @Valid 
  @Schema(name = "projectDeploymentWorkflows", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("projectDeploymentWorkflows")
  public List<@Valid ProjectDeploymentWorkflowModel> getProjectDeploymentWorkflows() {
    return projectDeploymentWorkflows;
  }

  public void setProjectDeploymentWorkflows(List<@Valid ProjectDeploymentWorkflowModel> projectDeploymentWorkflows) {
    this.projectDeploymentWorkflows = projectDeploymentWorkflows;
  }

  public ProjectDeploymentModel tags(List<@Valid TagModel> tags) {
    this.tags = tags;
    return this;
  }

  public ProjectDeploymentModel addTagsItem(TagModel tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
   */
  @Valid 
  @Schema(name = "tags", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tags")
  public List<@Valid TagModel> getTags() {
    return tags;
  }

  public void setTags(List<@Valid TagModel> tags) {
    this.tags = tags;
  }

  public ProjectDeploymentModel version(Integer version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * @return version
   */
  
  @Schema(name = "__version", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("__version")
  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectDeploymentModel projectDeployment = (ProjectDeploymentModel) o;
    return Objects.equals(this.createdBy, projectDeployment.createdBy) &&
        Objects.equals(this.createdDate, projectDeployment.createdDate) &&
        Objects.equals(this.description, projectDeployment.description) &&
        Objects.equals(this.enabled, projectDeployment.enabled) &&
        Objects.equals(this.environment, projectDeployment.environment) &&
        Objects.equals(this.id, projectDeployment.id) &&
        Objects.equals(this.lastExecutionDate, projectDeployment.lastExecutionDate) &&
        Objects.equals(this.lastModifiedBy, projectDeployment.lastModifiedBy) &&
        Objects.equals(this.lastModifiedDate, projectDeployment.lastModifiedDate) &&
        Objects.equals(this.name, projectDeployment.name) &&
        Objects.equals(this.projectId, projectDeployment.projectId) &&
        Objects.equals(this.projectVersion, projectDeployment.projectVersion) &&
        Objects.equals(this.project, projectDeployment.project) &&
        Objects.equals(this.projectDeploymentWorkflows, projectDeployment.projectDeploymentWorkflows) &&
        Objects.equals(this.tags, projectDeployment.tags) &&
        Objects.equals(this.version, projectDeployment.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdBy, createdDate, description, enabled, environment, id, lastExecutionDate, lastModifiedBy, lastModifiedDate, name, projectId, projectVersion, project, projectDeploymentWorkflows, tags, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDeploymentModel {\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
    sb.append("    environment: ").append(toIndentedString(environment)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastExecutionDate: ").append(toIndentedString(lastExecutionDate)).append("\n");
    sb.append("    lastModifiedBy: ").append(toIndentedString(lastModifiedBy)).append("\n");
    sb.append("    lastModifiedDate: ").append(toIndentedString(lastModifiedDate)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    projectVersion: ").append(toIndentedString(projectVersion)).append("\n");
    sb.append("    project: ").append(toIndentedString(project)).append("\n");
    sb.append("    projectDeploymentWorkflows: ").append(toIndentedString(projectDeploymentWorkflows)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

