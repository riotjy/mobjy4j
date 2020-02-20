package dev.riotjy.mobjy.export.codegen.cpp;

import java.util.HashMap;
import java.util.Iterator;

import dev.riotjy.mobjy.export.codegen.DeserializeClassCodeGenerator;
import dev.riotjy.mobjy.model.MjyPrimitive;
import dev.riotjy.mobjy.model.MjyType;

public class CppDeserializeClassCodeGenerator extends DeserializeClassCodeGenerator {

  public CppDeserializeClassCodeGenerator() {
  }

  public CppDeserializeClassCodeGenerator(String className, String generalizationName) {
    super(className, generalizationName);
  }

  public CppDeserializeClassCodeGenerator(String className, String generalizationName,
      HashMap<String, MjyType> attributesInfo, HashMap<String, MjyType> arraysInfo, HashMap<String, MjyType> mapsInfo) {
    super(className, generalizationName, attributesInfo, arraysInfo, mapsInfo);
  }

  @Override
  public String generate() {
    String code = 
        "  void deser" + className + "(jsonl::json & jo," + className + " value) {\n";
    if (null == generalizationName &&
        attributesInfo.isEmpty() &&
        arraysInfo.isEmpty() &&
        mapsInfo.isEmpty()) {
      code += "    // TODO: ADD DESERIALIZATION CODE HERE IF NECESSARY\n";
    }
    
    if (null != generalizationName) {
      code += "    deser" + generalizationName + "(jo, value);\n";
    }

    Iterator<String> it = attributesInfo.keySet().iterator();
    while (it.hasNext()) {
      String val = it.next();
      MjyType type = attributesInfo.get(val);
      if (type instanceof MjyPrimitive) {
        code += "    value->" + val + " = jo[\"" + val + "\"];\n";
      } else {
        code += "    value->" + val + " = std::dynamic_pointer_cast<" + type.getTypeName() + ">(deserObject(jo[\"" + val + "\"]));\n";
      }
    }

    it = arraysInfo.keySet().iterator();
    while (it.hasNext()) {
      String val = it.next();
//      code += "    value->" + val + " = deserArr(jo[" + val + "], value->" + attributesInfo.get(val) + ");\n";
      code += "    deserArr(jo[\"" + val + "\"], value->" + val + ");\n";
    }

    it = mapsInfo.keySet().iterator();
    while (it.hasNext()) {
      String val = it.next();
//      code += "    value->" + val + " = deserMap(jo[\"" + val + "\"], " + getPrmTypeStr(attributesInfo.get(val)) + ");\n";
      code += "    deserMap(jo[\"" + val + "\"], " + val + ");\n";
    }

    code += "  }\n";
    return code;
  }

}
