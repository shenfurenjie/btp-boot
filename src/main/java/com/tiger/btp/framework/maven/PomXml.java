package com.tiger.btp.framework.maven;

import lombok.Data;

@Data
public class PomXml {

    String name;

    String groupId;

    String artifactId;

    String version;

    String snapshotRepositoryURL;

    String repositoryURL;


}
