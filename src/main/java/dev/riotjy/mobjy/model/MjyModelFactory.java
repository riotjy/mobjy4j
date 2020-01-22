package dev.riotjy.mobjy.model;

public class MjyModelFactory {

  public MjyModel makeModel(String project, MjyVersion version, MjyVersion compatver) {
    MjyModel model = new MjyModel();
    model.setProject(project);
    model.setVersion(version);
    model.setCompatVersion(compatver);
    return model;
  }
  
  public MjyVersion makeVersion(
    int major, int minor, int revision, int candidate, String status) {
    return new MjyVersion(
        major, minor, revision, candidate, VersionStatus.getVersionStatus(status));
  }
}
