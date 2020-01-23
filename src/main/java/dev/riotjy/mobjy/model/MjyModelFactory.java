package dev.riotjy.mobjy.model;

public class MjyModelFactory {

  public static MjyModel makeModel(String project, MjyVersion version, MjyVersion compatver) {
    MjyModel model = new MjyModel();
    model.setProject(project);
    model.setVersion(version);
    model.setCompatVersion(compatver);
    return model;
  }
  
  public static MjyVersion makeVersion(
    int major, int minor, int revision, int candidate, String status) {
    return new MjyVersion(
        major, minor, revision, candidate, VersionStatus.getVersionStatus(status));
  }
  
  public static MjyClass makeClass(String className) {
    return new MjyClass(className);
  }
  
  public static MjyAttribute makeAttribute(String name, MjyType type) {
    return new MjyAttribute (name, type);
  }
  
  public static MjyCollection makeCollection(String name, MjyCollectionType type, MjyType valType) {
    return new MjyCollection(name, type, valType);
  }
  
  public static MjyType makePrimitive(MjyPrimitiveType type) {
    return new MjyPrimitive(type);
  }
  
  public static MjyType makeObject(MjyClass clazz) {
    return new MjyObject(clazz);
  }
}
