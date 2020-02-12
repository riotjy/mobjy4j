# mobjy for Java

A collection of utilities to generate java classes and de-/serializers from data model defined in YAML.

TODOs: Add "boolean" type, CPP de-/serializers, cosmetics, testing...

IMPORTANT NOTE:
The project is in an early stage. Please be kind to it! ;-)

TO CONTRIBUTORS:
You are welcome to contribute if you're interested and agree to ApL2.0.
Contact Alex for access.

## What does it currently do

RUNNING INSTRUCTIONS:

Run class "Mobjy" with parameter -i set to full path to input YAML file
(see examples/example.yaml in sister project mobjyArch)

Currently it turns this:
```YAML
# classes definition example
BaseClass:            #creates class BaseClass
  intOneBase: int
  intTwoBase: int
  intArrOne: int[]    #creates an array of integer primitives
  strOneBase: string
  strArrOne: string[] #creates an array list of strings
  flOneBase: float
  dblArrOne: double[] #creates an array list of single precision float primitives
  flArrBase: float[]
  dblArrOne: double[] #creates an array list of double precision floats primitives
  objOneBase:         #creates an object of type RefClassOne
    references: RefClassOne
  objArrTwoBase:      #creates an array list of objects of type RefClassTwo
    references: RefClassTwo
    collection: arrayList 
  objMapThreeBase:    #creates a hash map <string, RefClassTwo>
    references: RefClassTwo
    collection: hashMap
    
DervClassOne:   #creates class DervClassOne extending BaseClass
  extends: BaseClass  
  intOneDervOne: int
  strOneDervOne: string
  objOneDervOne:
    references: ExternalClassType
  mapOneDervOne:
    references: ExternalClassType4Coll
    collection: hashMap
  

DervClassTwo:   #creates class DervClassOne extending DervClassTwo
  extends: DervClassOne
  intOneDervTwo: int
  strOneDervTwo: string

RefClassOne:    #creates class RefClassOne
  intOneRefOne: int
  strOneRefOne: string

RefClassTwo:    #creates class RefClassTwo
  intOneRefTwo: int
  strOneRefTwo: string
  
ExternalClassType:    # an external class to be imported and used as type
   java:
      path: dev.riotj.demo.extclass.ExtClassJ  # import directive string
      class: ExtClassJ                         # actuall language dependent class name
   cpp:
      path: <dir1/dir2/ExtClassCpp>            # include directive string
      class: ExtClassCpp                       # actuall language dependent class name
   golang:
      path: dirA/dirB/ExtClassGo               # import directive string
      class: ExtClassGo                        # actuall language dependent class name

ExternalClassType4Coll:    # an external class to be imported and used as type
   java:
      path: dev.riotj.demo.extclass.ExtClass4CollJ
      class: ExtClass4CollJ
   cpp:
      path: <dir1/dir2/ExtClass4CollCpp>
      class: ExtClass4CollCpp
   golang:
      path: dirA/dirB/ExtClass4CollGo
      class: ExtClass4CollGo
```    
into this Java code: 

```Java
package dev.riotjy.testProject;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public  class BaseClass {
  protected Integer intOneBase;

  public Integer getIntOneBase() {
    return this.intOneBase;
  }

  public void setIntOneBase(Integer intOneBase) {
    this.intOneBase = intOneBase;
  }

  protected Integer intTwoBase;

  public Integer getIntTwoBase() {
    return this.intTwoBase;
  }

  public void setIntTwoBase(Integer intTwoBase) {
    this.intTwoBase = intTwoBase;
  }

  protected String strOneBase;

  public String getStrOneBase() {
    return this.strOneBase;
  }

  public void setStrOneBase(String strOneBase) {
    this.strOneBase = strOneBase;
  }

  protected Float flOneBase;

  public Float getFlOneBase() {
    return this.flOneBase;
  }

  public void setFlOneBase(Float flOneBase) {
    this.flOneBase = flOneBase;
  }

  protected RefClassOne objOneBase;

  public RefClassOne getObjOneBase() {
    return this.objOneBase;
  }

  public void setObjOneBase(RefClassOne objOneBase) {
    this.objOneBase = objOneBase;
  }

  protected List<Integer> intArrOne = new ArrayList<>();

  public int sizeIntArrOne() {
    return this.intArrOne.size();
  }

  public Integer getIntArrOne(int index) {
    if (index >= intArrOne.size())
      return null;
    return this.intArrOne.get(index);
  }

  public Integer setIntArrOne(int index, Integer value) {
    if (index >= intArrOne.size())
      return null;
    return this.intArrOne.set(index, value);
  }

  public Integer getIntArrOne(int index) {
    if (index >= intArrOne.size())
      return null;
    return this.intArrOne.remove(index);
  }

  public Integer popFrontIntArrOne() {
    if (0 == intArrOne.size())
      return null;
    return this.intArrOne.remove(0);
  }

  public void pushBackIntArrOne(Integer value) {
    this.intArrOne.add(value);
  }

  public Iterator iteratorIntArrOne() {
    return this.intArrOne.iterator();
  }

  protected List<String> strArrOne = new ArrayList<>();

  public int sizeStrArrOne() {
    return this.strArrOne.size();
  }

  public String getStrArrOne(int index) {
    if (index >= strArrOne.size())
      return null;
    return this.strArrOne.get(index);
  }

  public String setStrArrOne(int index, String value) {
    if (index >= strArrOne.size())
      return null;
    return this.strArrOne.set(index, value);
  }

  public String getStrArrOne(int index) {
    if (index >= strArrOne.size())
      return null;
    return this.strArrOne.remove(index);
  }

  public String popFrontStrArrOne() {
    if (0 == strArrOne.size())
      return null;
    return this.strArrOne.remove(0);
  }

  public void pushBackStrArrOne(String value) {
    this.strArrOne.add(value);
  }

  public Iterator iteratorStrArrOne() {
    return this.strArrOne.iterator();
  }

  protected List<Float> flArrBase = new ArrayList<>();

  public int sizeFlArrBase() {
    return this.flArrBase.size();
  }

  public Float getFlArrBase(int index) {
    if (index >= flArrBase.size())
      return null;
    return this.flArrBase.get(index);
  }

  public Float setFlArrBase(int index, Float value) {
    if (index >= flArrBase.size())
      return null;
    return this.flArrBase.set(index, value);
  }

  public Float getFlArrBase(int index) {
    if (index >= flArrBase.size())
      return null;
    return this.flArrBase.remove(index);
  }

  public Float popFrontFlArrBase() {
    if (0 == flArrBase.size())
      return null;
    return this.flArrBase.remove(0);
  }

  public void pushBackFlArrBase(Float value) {
    this.flArrBase.add(value);
  }

  public Iterator iteratorFlArrBase() {
    return this.flArrBase.iterator();
  }

  protected List<Double> dblArrOne = new ArrayList<>();

  public int sizeDblArrOne() {
    return this.dblArrOne.size();
  }

  public Double getDblArrOne(int index) {
    if (index >= dblArrOne.size())
      return null;
    return this.dblArrOne.get(index);
  }

  public Double setDblArrOne(int index, Double value) {
    if (index >= dblArrOne.size())
      return null;
    return this.dblArrOne.set(index, value);
  }

  public Double getDblArrOne(int index) {
    if (index >= dblArrOne.size())
      return null;
    return this.dblArrOne.remove(index);
  }

  public Double popFrontDblArrOne() {
    if (0 == dblArrOne.size())
      return null;
    return this.dblArrOne.remove(0);
  }

  public void pushBackDblArrOne(Double value) {
    this.dblArrOne.add(value);
  }

  public Iterator iteratorDblArrOne() {
    return this.dblArrOne.iterator();
  }

  protected List<RefClassTwo> objArrTwoBase = new ArrayList<>();

  public int sizeObjArrTwoBase() {
    return this.objArrTwoBase.size();
  }

  public RefClassTwo getObjArrTwoBase(int index) {
    if (index >= objArrTwoBase.size())
      return null;
    return this.objArrTwoBase.get(index);
  }

  public RefClassTwo setObjArrTwoBase(int index, RefClassTwo value) {
    if (index >= objArrTwoBase.size())
      return null;
    return this.objArrTwoBase.set(index, value);
  }

  public RefClassTwo getObjArrTwoBase(int index) {
    if (index >= objArrTwoBase.size())
      return null;
    return this.objArrTwoBase.remove(index);
  }

  public RefClassTwo popFrontObjArrTwoBase() {
    if (0 == objArrTwoBase.size())
      return null;
    return this.objArrTwoBase.remove(0);
  }

  public void pushBackObjArrTwoBase(RefClassTwo value) {
    this.objArrTwoBase.add(value);
  }

  public Iterator iteratorObjArrTwoBase() {
    return this.objArrTwoBase.iterator();
  }

  protected HashMap<String, RefClassTwo> objMapThreeBase = new HashMap<>();

  public int sizeOfObjMapThreeBase() {
    return this.objMapThreeBase.size();
  }

  public RefClassTwo getObjMapThreeBase(String key) {
    return this.objMapThreeBase.get(key);
  }

  public void putObjMapThreeBase(String key, RefClassTwo value) {
    this.objMapThreeBase.put(key, value);
  }

  public RefClassTwo removeObjMapThreeBase(String key) {
    return this.objMapThreeBase.remove(key);
  }

  public Set<String> keySetObjMapThreeBase() {
    return this.objMapThreeBase.keySet();
  }

}

********
package dev.riotjy.testProject;

import java.util.Map;
import java.util.HashMap;

import dev.riotj.demo.extclass.ExtClassJ;
import dev.riotj.demo.extclass.ExtClass4CollJ;


public  class DervClassOne extends BaseClass {
  protected Integer intOneDervOne;

  public Integer getIntOneDervOne() {
    return this.intOneDervOne;
  }

  public void setIntOneDervOne(Integer intOneDervOne) {
    this.intOneDervOne = intOneDervOne;
  }

  protected String strOneDervOne;

  public String getStrOneDervOne() {
    return this.strOneDervOne;
  }

  public void setStrOneDervOne(String strOneDervOne) {
    this.strOneDervOne = strOneDervOne;
  }

  protected ExtClassJ objOneDervOne;

  public ExtClassJ getObjOneDervOne() {
    return this.objOneDervOne;
  }

  public void setObjOneDervOne(ExtClassJ objOneDervOne) {
    this.objOneDervOne = objOneDervOne;
  }

  protected HashMap<String, ExtClass4CollJ> mapOneDervOne = new HashMap<>();

  public int sizeOfMapOneDervOne() {
    return this.mapOneDervOne.size();
  }

  public ExtClass4CollJ getMapOneDervOne(String key) {
    return this.mapOneDervOne.get(key);
  }

  public void putMapOneDervOne(String key, ExtClass4CollJ value) {
    this.mapOneDervOne.put(key, value);
  }

  public ExtClass4CollJ removeMapOneDervOne(String key) {
    return this.mapOneDervOne.remove(key);
  }

  public Set<String> keySetMapOneDervOne() {
    return this.mapOneDervOne.keySet();
  }

}

********
package dev.riotjy.testProject;


public  class DervClassTwo extends DervClassOne {
  protected Integer intOneDervTwo;

  public Integer getIntOneDervTwo() {
    return this.intOneDervTwo;
  }

  public void setIntOneDervTwo(Integer intOneDervTwo) {
    this.intOneDervTwo = intOneDervTwo;
  }

  protected String strOneDervTwo;

  public String getStrOneDervTwo() {
    return this.strOneDervTwo;
  }

  public void setStrOneDervTwo(String strOneDervTwo) {
    this.strOneDervTwo = strOneDervTwo;
  }

}

********
package dev.riotjy.testProject;


public  class RefClassOne {
  protected Integer intOneRefOne;

  public Integer getIntOneRefOne() {
    return this.intOneRefOne;
  }

  public void setIntOneRefOne(Integer intOneRefOne) {
    this.intOneRefOne = intOneRefOne;
  }

  protected String strOneRefOne;

  public String getStrOneRefOne() {
    return this.strOneRefOne;
  }

  public void setStrOneRefOne(String strOneRefOne) {
    this.strOneRefOne = strOneRefOne;
  }

}

********
package dev.riotjy.testProject;


public  class RefClassTwo {
  protected Integer intOneRefTwo;

  public Integer getIntOneRefTwo() {
    return this.intOneRefTwo;
  }

  public void setIntOneRefTwo(Integer intOneRefTwo) {
    this.intOneRefTwo = intOneRefTwo;
  }

  protected String strOneRefTwo;

  public String getStrOneRefTwo() {
    return this.strOneRefTwo;
  }

  public void setStrOneRefTwo(String strOneRefTwo) {
    this.strOneRefTwo = strOneRefTwo;
  }

}

$$$$$$$$$$$$$$$$$$$$$$$
package dev.riotjy.testProject;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.util.Iterator;


public  class TestProjectSerializer {
  private static String serBaseClass(BaseClass value) {
    return
        lin(con(qtd(intOneBase), serValue(value.intOneBase))) +
        lin(con(qtd(intTwoBase), serValue(value.intTwoBase))) +
        lin(con(qtd(strOneBase), serValue(value.strOneBase))) +
        lin(con(qtd(flOneBase), serValue(value.flOneBase))) +
        lin(con(qtd(objOneBase), serValue(value.objOneBase))) +
        lin(con(qtd(intArrOne), serArr(value.intArrOne))) +
        lin(con(qtd(strArrOne), serArr(value.strArrOne))) +
        lin(con(qtd(flArrBase), serArr(value.flArrBase))) +
        lin(con(qtd(dblArrOne), serArr(value.dblArrOne))) +
        lin(con(qtd(objArrTwoBase), serArr(value.objArrTwoBase))) +
        con(qtd(objMapThreeBase), serMap(value.objMapThreeBase));
  }

  private static String serDervClassOne(DervClassOne value) {
    return
        lin(serBaseClass(BaseClass value)) +
        lin(con(qtd(intOneDervOne), serValue(value.intOneDervOne))) +
        lin(con(qtd(strOneDervOne), serValue(value.strOneDervOne))) +
        lin(con(qtd(objOneDervOne), serValue(value.objOneDervOne))) +
        con(qtd(mapOneDervOne), serMap(value.mapOneDervOne));
  }

  private static String serDervClassTwo(DervClassTwo value) {
    return
        lin(serDervClassOne(DervClassOne value)) +
        lin(con(qtd(intOneDervTwo), serValue(value.intOneDervTwo))) +
        con(qtd(strOneDervTwo), serValue(value.strOneDervTwo));
  }

  private static String serRefClassOne(RefClassOne value) {
    return
        lin(con(qtd(intOneRefOne), serValue(value.intOneRefOne))) +
        con(qtd(strOneRefOne), serValue(value.strOneRefOne));
  }

  private static String serRefClassTwo(RefClassTwo value) {
    return
        lin(con(qtd(intOneRefTwo), serValue(value.intOneRefTwo))) +
        con(qtd(strOneRefTwo), serValue(value.strOneRefTwo));
  }

  private static String serExternalClassType(ExternalClassType value) {
    return
        // TODO: REPLACE WITH SERIALIZATION CODE FOR CLASS ExternalClassType
        "";
  }

  private static String serExternalClassType4Coll(ExternalClassType4Coll value) {
    return
        // TODO: REPLACE WITH SERIALIZATION CODE FOR CLASS ExternalClassType4Coll
        "";
  }

  private static String serValue(Object value) {
    
    if (null == value)
      return "null";
    
    if (value instanceof Boolean ||
        value instanceof Integer ||
        value instanceof Float ||
        value instanceof Double) {
      return value.toString();
    }
    
    if (value instanceof String) {
      return "\"" + ((String)value).toString() + "\"";
    }

    String className = value.getClass().getSimpleName();
    switch(className) {
    case "BaseClass":
      return serClass(className,serBaseClass((BaseClass)value));
    case "DervClassOne":
      return serClass(className,serDervClassOne((DervClassOne)value));
    case "DervClassTwo":
      return serClass(className,serDervClassTwo((DervClassTwo)value));
    case "RefClassOne":
      return serClass(className,serRefClassOne((RefClassOne)value));
    case "RefClassTwo":
      return serClass(className,serRefClassTwo((RefClassTwo)value));
    case "ExternalClassType":
      return serClass(className,serExternalClassType((ExternalClassType)value));
    case "ExternalClassType4Coll":
      return serClass(className,serExternalClassType4Coll((ExternalClassType4Coll)value));
    default:
      return "";
    }
  }

  private static String serClass(String className, String fields) {
    return "{\"cnid\":\"" + className + "\"," + fields + "}";
  }

  private static String serArr(ArrayList<?> arr) {
    String json = "[";
    Iterator<?> it = arr.iterator();
    
    while (it.hasNext()) {
      Object val = it.next();
      if (it.hasNext()) {
        json += lin(serValue(val)); 
      } else {
        json += serValue(val); 
      }
    }
    
    json += "]";
    return json;
  }

  private static String serMap(HashMap<String, ?> hm) {
    String json = "{";

    Iterator<String> it = hm.keySet().iterator();
   
    while (it.hasNext()) {
      String key = it.next();
      Object val = hm.get(key);
      if (it.hasNext()) {
        json += lin(con(qtd(key), serValue(val))); 
      } else {
        json += con(qtd(key), serValue(val)); 
      }
    }
    
    json += "}";
    return json;
  }
  
  private static String qtd(String in) {
    return "\"" + in + "\"";
  }
  private static String con(String first, String second) {
    return first + ":" + second;
  }
  private static String lin(String in) {
    return in + ",";
  }
  private static String crl(String in) {
    return "{" + in + "}";
  }
  
  public static String serialize(Object value) {
    return serValue(value);
  }

}

#########################
package dev.riotjy.testProject;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.util.Iterator;
import java.util.Map.Entry;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;


public  class TestProjectDeserializer {
  private static void deserBaseClass(JsonObject jo,BaseClass value) {
    value.strOneBase = jo.get("strOneBase").getAsString();
    value.flOneBase = jo.get("flOneBase").getAsFloat();
    value.intOneBase = jo.get("intOneBase").getAsInt();
    value.objOneBase = (RefClassOne)deserObject(jo.get("objOneBase").getAsJsonObject());
    value.intTwoBase = jo.get("intTwoBase").getAsInt();
    value.objArrTwoBase = deserArr(jo.get("objArrTwoBase").getAsJsonArray(), null);
    value.strArrOne = deserArr(jo.get("strArrOne").getAsJsonArray(), null);
    value.dblArrOne = deserArr(jo.get("dblArrOne").getAsJsonArray(), null);
    value.intArrOne = deserArr(jo.get("intArrOne").getAsJsonArray(), null);
    value.flArrBase = deserArr(jo.get("flArrBase").getAsJsonArray(), null);
    value.objMapThreeBase = deserMap(jo.get("objMapThreeBase").getAsJsonObject(), null);
  }

  private static void deserDervClassOne(JsonObject jo,DervClassOne value) {
    deserBaseClass(jo, value);
    value.intOneDervOne = jo.get("intOneDervOne").getAsInt();
    value.strOneDervOne = jo.get("strOneDervOne").getAsString();
    value.objOneDervOne = (ExternalClassType)deserObject(jo.get("objOneDervOne").getAsJsonObject());
    value.mapOneDervOne = deserMap(jo.get("mapOneDervOne").getAsJsonObject(), null);
  }

  private static void deserDervClassTwo(JsonObject jo,DervClassTwo value) {
    deserDervClassOne(jo, value);
    value.intOneDervTwo = jo.get("intOneDervTwo").getAsInt();
    value.strOneDervTwo = jo.get("strOneDervTwo").getAsString();
  }

  private static void deserRefClassOne(JsonObject jo,RefClassOne value) {
    value.intOneRefOne = jo.get("intOneRefOne").getAsInt();
    value.strOneRefOne = jo.get("strOneRefOne").getAsString();
  }

  private static void deserRefClassTwo(JsonObject jo,RefClassTwo value) {
    value.intOneRefTwo = jo.get("intOneRefTwo").getAsInt();
    value.strOneRefTwo = jo.get("strOneRefTwo").getAsString();
  }

  private static void deserExternalClassType(JsonObject jo,ExternalClassType value) {
    // TODO: ADD DESERIALIZATION CODE HERE IF NECESSARY
  }

  private static void deserExternalClassType4Coll(JsonObject jo,ExternalClassType4Coll value) {
    // TODO: ADD DESERIALIZATION CODE HERE IF NECESSARY
  }

  private static Object deserObject(JsonObject jo) {
    
    String cnid = jo.get("cnid").getAsString();
    
    switch (cnid) {
    case "BaseClass":
      BaseClass obj = new BaseClass();
      deserBaseClass(jo ,obj);
      return obj;
    case "DervClassOne":
      DervClassOne obj = new DervClassOne();
      deserDervClassOne(jo ,obj);
      return obj;
    case "DervClassTwo":
      DervClassTwo obj = new DervClassTwo();
      deserDervClassTwo(jo ,obj);
      return obj;
    case "RefClassOne":
      RefClassOne obj = new RefClassOne();
      deserRefClassOne(jo ,obj);
      return obj;
    case "RefClassTwo":
      RefClassTwo obj = new RefClassTwo();
      deserRefClassTwo(jo ,obj);
      return obj;
    case "ExternalClassType":
      ExternalClassType obj = new ExternalClassType();
      deserExternalClassType(jo ,obj);
      return obj;
    case "ExternalClassType4Coll":
      ExternalClassType4Coll obj = new ExternalClassType4Coll();
      deserExternalClassType4Coll(jo ,obj);
      return obj;
    default:
      return null;
    }
  }

  private static Object deserPrimitive(JsonPrimitive jp, MjyPrimitiveType prType) {
    if (null == prType) {
      return jp.getAsString();
    }
    switch (prType) {
    case BOOLEAN:
      return Boolean.valueOf(jp.getAsBoolean());
    case INT:
      return Integer.valueOf(jp.getAsInt());
    case FLOAT:
      return Float.valueOf(jp.getAsFloat());
    case DOUBLE:
      return Double.valueOf(jp.getAsDouble());
    case STRING:
      return jp.getAsString();
    }
    return null;
  }

  private static ArrayList deserArr(JsonArray ja, MjyPrimitiveType prType) {
    ArrayList<Object> arr = new ArrayList<Object>();
    
    for (JsonElement el : ja) {
      if (el.isJsonObject())
      {
        arr.add(deserObject(el.getAsJsonObject()));
        continue;
      }
      if (el.isJsonPrimitive()) {
        arr.add(deserPrimitive(el.getAsJsonPrimitive(), prType));
      }
    }
    
    return arr;
  }

  private static HashMap deserMap(JsonObject jo, MjyPrimitiveType prType) {
    HashMap<String, Object> hm = new HashMap<>();
    
    Iterator<Entry<String, JsonElement>> it = jo.entrySet().iterator();
    
    while (it.hasNext()) {
      Entry<String, JsonElement> en = it.next();
      JsonElement el = en.getValue();
      if (el.isJsonObject()) {
        hm.put(en.getKey(), deserObject(el.getAsJsonObject()));
        continue;
      }
      if (el.isJsonPrimitive()) {
        hm.put(en.getKey(), deserPrimitive(el.getAsJsonPrimitive(), prType));
      }
    }
    
    return hm;
  }

  public static Object deserialize(JsonObject jo) {
    return deserObject(jo);
  }
  
  public static Object deserialize(String json) {
    return deserialize(new JsonParser().parse(json).getAsJsonObject());
  }
}
```
and this CPP code:

```CPP
#include <cstdint>
#include <string>
#include <stdexcept>
#include <vector>
#include <map>
#include "RefClassOne.hpp"
#include "RefClassTwo.hpp"


#pragma once
#ifndef BaseClass_hpp
#define BaseClass_hpp

#namespace testproject {

class BaseClass {
public:
  std::string className() const {return "BaseClass";}

protected:
  uint64_t intOneBase;
public:
  uint64_t getIntOneBase() {
    return this->intOneBase;
  }

  void setIntOneBase(uint64_t intOneBase) {
    this->intOneBase = intOneBase;
  }

protected:
  uint64_t intTwoBase;
public:
  uint64_t getIntTwoBase() {
    return this->intTwoBase;
  }

  void setIntTwoBase(uint64_t intTwoBase) {
    this->intTwoBase = intTwoBase;
  }

protected:
  std::string strOneBase;
public:
  std::string getStrOneBase() {
    return this->strOneBase;
  }

  void setStrOneBase(std::string strOneBase) {
    this->strOneBase = strOneBase;
  }

protected:
  float flOneBase;
public:
  float getFlOneBase() {
    return this->flOneBase;
  }

  void setFlOneBase(float flOneBase) {
    this->flOneBase = flOneBase;
  }

protected:
  RefClassOne objOneBase;
public:
  RefClassOne getObjOneBase() {
    return this->objOneBase;
  }

  void setObjOneBase(RefClassOne objOneBase) {
    this->objOneBase = objOneBase;
  }

protected:
  std::vector<uint64_t> intArrOne;

public:
  std::vector<uint64_t>::size_type sizeIntArrOne() {
    return this->intArrOne.size();
  }

  uint64_t & atIntArrOne(std::vector<uint64_t>::size_type pos) {
    if (pos >= intArrOne.size())
      throw std::invalid_argument("Vector position/index out of bounds!");
    return this->intArrOne.at(pos);
  }

  uint64_t & popFrontIntArrOne() {
    if (0 == intArrOne.size())
      throw std::length_error("Vector is empty!");
    uint64_t & ret = this->intArrOne.front();
    this->intArrOne.erase(intArrOne.begin());
    return ret;
  }

  void pushBackIntArrOne(uint64_t value) {
    this->intArrOne.push_back(value);
  }

  std::vector<uint64_t>::const_iterator iteratorIntArrOne() {
    return this->intArrOne.cbegin();
  }

protected:
  std::vector<std::string> strArrOne;

public:
  std::vector<std::string>::size_type sizeStrArrOne() {
    return this->strArrOne.size();
  }

  std::string & atStrArrOne(std::vector<std::string>::size_type pos) {
    if (pos >= strArrOne.size())
      throw std::invalid_argument("Vector position/index out of bounds!");
    return this->strArrOne.at(pos);
  }

  std::string & popFrontStrArrOne() {
    if (0 == strArrOne.size())
      throw std::length_error("Vector is empty!");
    std::string & ret = this->strArrOne.front();
    this->strArrOne.erase(strArrOne.begin());
    return ret;
  }

  void pushBackStrArrOne(std::string value) {
    this->strArrOne.push_back(value);
  }

  std::vector<std::string>::const_iterator iteratorStrArrOne() {
    return this->strArrOne.cbegin();
  }

protected:
  std::vector<float> flArrBase;

public:
  std::vector<float>::size_type sizeFlArrBase() {
    return this->flArrBase.size();
  }

  float & atFlArrBase(std::vector<float>::size_type pos) {
    if (pos >= flArrBase.size())
      throw std::invalid_argument("Vector position/index out of bounds!");
    return this->flArrBase.at(pos);
  }

  float & popFrontFlArrBase() {
    if (0 == flArrBase.size())
      throw std::length_error("Vector is empty!");
    float & ret = this->flArrBase.front();
    this->flArrBase.erase(flArrBase.begin());
    return ret;
  }

  void pushBackFlArrBase(float value) {
    this->flArrBase.push_back(value);
  }

  std::vector<float>::const_iterator iteratorFlArrBase() {
    return this->flArrBase.cbegin();
  }

protected:
  std::vector<double> dblArrOne;

public:
  std::vector<double>::size_type sizeDblArrOne() {
    return this->dblArrOne.size();
  }

  double & atDblArrOne(std::vector<double>::size_type pos) {
    if (pos >= dblArrOne.size())
      throw std::invalid_argument("Vector position/index out of bounds!");
    return this->dblArrOne.at(pos);
  }

  double & popFrontDblArrOne() {
    if (0 == dblArrOne.size())
      throw std::length_error("Vector is empty!");
    double & ret = this->dblArrOne.front();
    this->dblArrOne.erase(dblArrOne.begin());
    return ret;
  }

  void pushBackDblArrOne(double value) {
    this->dblArrOne.push_back(value);
  }

  std::vector<double>::const_iterator iteratorDblArrOne() {
    return this->dblArrOne.cbegin();
  }

protected:
  std::vector<RefClassTwo> objArrTwoBase;

public:
  std::vector<RefClassTwo>::size_type sizeObjArrTwoBase() {
    return this->objArrTwoBase.size();
  }

  RefClassTwo & atObjArrTwoBase(std::vector<RefClassTwo>::size_type pos) {
    if (pos >= objArrTwoBase.size())
      throw std::invalid_argument("Vector position/index out of bounds!");
    return this->objArrTwoBase.at(pos);
  }

  RefClassTwo & popFrontObjArrTwoBase() {
    if (0 == objArrTwoBase.size())
      throw std::length_error("Vector is empty!");
    RefClassTwo & ret = this->objArrTwoBase.front();
    this->objArrTwoBase.erase(objArrTwoBase.begin());
    return ret;
  }

  void pushBackObjArrTwoBase(RefClassTwo value) {
    this->objArrTwoBase.push_back(value);
  }

  std::vector<RefClassTwo>::const_iterator iteratorObjArrTwoBase() {
    return this->objArrTwoBase.cbegin();
  }

protected:
  std::map<std::string, RefClassTwo> objMapThreeBase;

public:
  std::map<std::string, RefClassTwo>::size_type sizeObjMapThreeBase() {
    return this->objMapThreeBase.size();
  }

  RefClassTwo & getObjMapThreeBase(std::string key) {
    return this->objMapThreeBase[key];
  }

  bool putObjMapThreeBase(std::string key, RefClassTwo value) {
    auto retPair = this->objMapThreeBase.emplace(key, value);
    return retPair.second;
  }

  std::map<std::string, RefClassTwo>::size_type eraseObjMapThreeBase(std::string key) {
    return this->objMapThreeBase.erase(key);
  }

  std::map<std::string, RefClassTwo>::const_iterator iteratorObjMapThreeBase() {
    return this->objMapThreeBase.cbegin();
  }

}; // class BaseClass


} // namespace testproject
#endif // BaseClass_hpp

+++++++++++


[main] INFO dev.riotjy.mobjy.export.ModelExporter - 
+++++++++++
#include <cstdint>
#include <string>
#include <stdexcept>
#include <map>
#include <dir1/dir2/ExtClassCpp>
#include "dir1/dir2/ExtClass4CollCpp"
#include "BaseClass.hpp"


#pragma once
#ifndef DervClassOne_hpp
#define DervClassOne_hpp

#namespace testproject {

class DervClassOne: public BaseClass {
public:
  std::string className() const {return "DervClassOne";}

protected:
  uint64_t intOneDervOne;
public:
  uint64_t getIntOneDervOne() {
    return this->intOneDervOne;
  }

  void setIntOneDervOne(uint64_t intOneDervOne) {
    this->intOneDervOne = intOneDervOne;
  }

protected:
  std::string strOneDervOne;
public:
  std::string getStrOneDervOne() {
    return this->strOneDervOne;
  }

  void setStrOneDervOne(std::string strOneDervOne) {
    this->strOneDervOne = strOneDervOne;
  }

protected:
  ExtClassCpp objOneDervOne;
public:
  ExtClassCpp getObjOneDervOne() {
    return this->objOneDervOne;
  }

  void setObjOneDervOne(ExtClassCpp objOneDervOne) {
    this->objOneDervOne = objOneDervOne;
  }

protected:
  std::map<std::string, ExtClass4CollCpp> mapOneDervOne;

public:
  std::map<std::string, ExtClass4CollCpp>::size_type sizeMapOneDervOne() {
    return this->mapOneDervOne.size();
  }

  ExtClass4CollCpp & getMapOneDervOne(std::string key) {
    return this->mapOneDervOne[key];
  }

  bool putMapOneDervOne(std::string key, ExtClass4CollCpp value) {
    auto retPair = this->mapOneDervOne.emplace(key, value);
    return retPair.second;
  }

  std::map<std::string, ExtClass4CollCpp>::size_type eraseMapOneDervOne(std::string key) {
    return this->mapOneDervOne.erase(key);
  }

  std::map<std::string, ExtClass4CollCpp>::const_iterator iteratorMapOneDervOne() {
    return this->mapOneDervOne.cbegin();
  }

}; // class DervClassOne


} // namespace testproject
#endif // DervClassOne_hpp

+++++++++++
#include <cstdint>
#include <string>
#include "DervClassOne.hpp"


#pragma once
#ifndef DervClassTwo_hpp
#define DervClassTwo_hpp

#namespace testproject {

class DervClassTwo: public DervClassOne {
public:
  std::string className() const {return "DervClassTwo";}

protected:
  uint64_t intOneDervTwo;
public:
  uint64_t getIntOneDervTwo() {
    return this->intOneDervTwo;
  }

  void setIntOneDervTwo(uint64_t intOneDervTwo) {
    this->intOneDervTwo = intOneDervTwo;
  }

protected:
  std::string strOneDervTwo;
public:
  std::string getStrOneDervTwo() {
    return this->strOneDervTwo;
  }

  void setStrOneDervTwo(std::string strOneDervTwo) {
    this->strOneDervTwo = strOneDervTwo;
  }

}; // class DervClassTwo


} // namespace testproject
#endif // DervClassTwo_hpp

+++++++++++
#include <cstdint>
#include <string>

#pragma once
#ifndef RefClassOne_hpp
#define RefClassOne_hpp

#namespace testproject {

class RefClassOne {
public:
  std::string className() const {return "RefClassOne";}

protected:
  uint64_t intOneRefOne;
public:
  uint64_t getIntOneRefOne() {
    return this->intOneRefOne;
  }

  void setIntOneRefOne(uint64_t intOneRefOne) {
    this->intOneRefOne = intOneRefOne;
  }

protected:
  std::string strOneRefOne;
public:
  std::string getStrOneRefOne() {
    return this->strOneRefOne;
  }

  void setStrOneRefOne(std::string strOneRefOne) {
    this->strOneRefOne = strOneRefOne;
  }

}; // class RefClassOne


} // namespace testproject
#endif // RefClassOne_hpp

+++++++++++
#include <cstdint>
#include <string>

#pragma once
#ifndef RefClassTwo_hpp
#define RefClassTwo_hpp

#namespace testproject {

class RefClassTwo {
public:
  std::string className() const {return "RefClassTwo";}

protected:
  uint64_t intOneRefTwo;
public:
  uint64_t getIntOneRefTwo() {
    return this->intOneRefTwo;
  }

  void setIntOneRefTwo(uint64_t intOneRefTwo) {
    this->intOneRefTwo = intOneRefTwo;
  }

protected:
  std::string strOneRefTwo;
public:
  std::string getStrOneRefTwo() {
    return this->strOneRefTwo;
  }

  void setStrOneRefTwo(std::string strOneRefTwo) {
    this->strOneRefTwo = strOneRefTwo;
  }

}; // class RefClassTwo


} // namespace testproject
#endif // RefClassTwo_hpp
```