# **mobjy** for Java

**mobjy** for Java is a tool that accepts a data model specification in YAML, and outputs Java and CPP embeddable code comprised of
- the data model entities hierarchy as OOP classes
- a serializer utility to convert in process data objects to JSON 
- a deserializer utility to convert from JSON to in process data objects

**mobjy** supports object oriented hierarchies (inheritance) making the conversion between in process data objects and JSON data possible while ensuring the creation of the intended in process class instances.
This is somethig that existing JSON libraries don't support automatically. Mobjy makes this possible by embedding a class identifier with each JSON data object.

In its current verison **mobjy** is using the *gson* and *nlohmann/json* for parsing JSON data.
It avoids using automatic object creation provided by these libraries in order to provide interchangeable parsers in the future (e.g. *jackson*, *jsoncpp*).

**mobjy** is intended to be extended for other languages too (golang, python, etc...), this depends on available time and resources to continue development.

**"mobjy"** loosely stands for **m**odelling **ob**jects for **j**son with **y**aml.  

KEYWORDS: JSON inheritance support, JSON data model, JSON data modelling, JSON object hierarchy, JSON serialize deserialize,
Oject Oriented JSON

IMPORTANT NOTE:
The project is in an early stage. Please be kind to it! ;-)

TO CONTRIBUTORS:
You are welcome to contribute if you're interested and agree to ApL2.0.
Contact Alex for access. Message to [ r i o t j y   p r o t o n m a i l   c o m ] (fill in missing info).

## What does it currently do

BUILD INSTRUCTIONS:
Gradle: run gradlew.* script, specify desired task
Eclipse: install buildship extension for Gradle support, import as existing gradle project, build project

RUNNING INSTRUCTIONS:
Run class "Mobjy" with parameter -i set to the full/relative path to input YAML file and -o parameter to the output directory/folder.
**mpbjy** will generate a bunch of java and cpp files. In order to use them, the gson library for java and the nlohmann/json (json.hpp) library for cpp are needed.

Using ./examples/example.yaml in project directory, currently it turns this YAML class model definition:
```YAML

# classes definition example
BaseClass:            #creates class BaseClass
  boolOneBase: bool
  boolArrOne: bool[]
  intOneBase: int
  intTwoBase: int
  intArrOne: int[]    #creates an array of integer primitives
  strOneBase: string
  strArrOne: string[] #creates an array list of strings
  flOneBase: float
  dblOneBase: double #creates an array list of single precision float primitives
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
#  intOneDervOne: int
#  strOneDervOne: string
  objOneDervOne:
    references: RefClassOne
    collection: arrayList
  mapOneDervOne:
    references: RefClassTwo
    collection: hashMap
  

DervClassTwo:   #creates class DervClassOne extending DervClassTwo
  extends: DervClassOne
  intOneDervTwo: int
  strOneDervTwo: string
  objOneDervTwo:
    references: ExternalClassType
  mapOneDervTwo:
    references: ExternalClassType4Coll
    collection: hashMap

RefClassOne:    #creates class RefClassOne
  intOneRefOne: int
  strOneRefOne: string

RefClassTwo:    #creates class RefClassTwo
  intOneRefTwo: int
  strOneRefTwo: string
  
ExternalClassType:    # an external class to be imported and used as type
   java:
      path: dev.riotjy.demo.extclass.ExtClassJ  # import directive string
      class: ExtClassJ                         # actuall language dependent class name
   cpp:
      path: '"ext/ExtClassCpp.hpp"'            # include directive string
      class: ExtClassCpp                       # actuall language dependent class name

ExternalClassType4Coll:    # an external class to be imported and used as type
   java:
      path: dev.riotjy.demo.extclass.ExtClass4CollJ
      class: ExtClass4CollJ
   cpp:
      path: '"ext/ExtClass4CollCpp.hpp"'     # using single quotes to specify relative path
      class: ExtClass4CollCpp
```    
into this Java code: 

```Java
package dev.riotjy.testProject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;

public  class BaseClass {
  protected Boolean boolOneBase;

  public Boolean getBoolOneBase() {
    return this.boolOneBase;
  }

  public void setBoolOneBase(Boolean boolOneBase) {
    this.boolOneBase = boolOneBase;
  }

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

  protected Double dblOneBase;

  public Double getDblOneBase() {
    return this.dblOneBase;
  }

  public void setDblOneBase(Double dblOneBase) {
    this.dblOneBase = dblOneBase;
  }

  protected RefClassOne objOneBase;

  public RefClassOne getObjOneBase() {
    return this.objOneBase;
  }

  public void setObjOneBase(RefClassOne objOneBase) {
    this.objOneBase = objOneBase;
  }

  protected ArrayList<Boolean> boolArrOne = new ArrayList<>();

  public int sizeBoolArrOne() {
    return this.boolArrOne.size();
  }

  public Boolean getBoolArrOne(int index) {
    if (index >= boolArrOne.size())
      return null;
    return this.boolArrOne.get(index);
  }

  public Boolean setBoolArrOne(int index, Boolean value) {
    if (index >= boolArrOne.size())
      return null;
    return this.boolArrOne.set(index, value);
  }

  public Boolean removeBoolArrOne(int index) {
    if (index >= boolArrOne.size())
      return null;
    return this.boolArrOne.remove(index);
  }

  public Boolean popFrontBoolArrOne() {
    if (0 == boolArrOne.size())
      return null;
    return this.boolArrOne.remove(0);
  }

  public void pushBackBoolArrOne(Boolean value) {
    this.boolArrOne.add(value);
  }

  public Iterator iteratorBoolArrOne() {
    return this.boolArrOne.iterator();
  }

  protected ArrayList<Integer> intArrOne = new ArrayList<>();

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

  public Integer removeIntArrOne(int index) {
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

  protected ArrayList<String> strArrOne = new ArrayList<>();

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

  public String removeStrArrOne(int index) {
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

  protected ArrayList<Float> flArrBase = new ArrayList<>();

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

  public Float removeFlArrBase(int index) {
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

  protected ArrayList<Double> dblArrOne = new ArrayList<>();

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

  public Double removeDblArrOne(int index) {
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

  protected ArrayList<RefClassTwo> objArrTwoBase = new ArrayList<>();

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

  public RefClassTwo removeObjArrTwoBase(int index) {
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;

public  class DervClassOne extends BaseClass {
  protected ArrayList<RefClassOne> objOneDervOne = new ArrayList<>();

  public int sizeObjOneDervOne() {
    return this.objOneDervOne.size();
  }

  public RefClassOne getObjOneDervOne(int index) {
    if (index >= objOneDervOne.size())
      return null;
    return this.objOneDervOne.get(index);
  }

  public RefClassOne setObjOneDervOne(int index, RefClassOne value) {
    if (index >= objOneDervOne.size())
      return null;
    return this.objOneDervOne.set(index, value);
  }

  public RefClassOne removeObjOneDervOne(int index) {
    if (index >= objOneDervOne.size())
      return null;
    return this.objOneDervOne.remove(index);
  }

  public RefClassOne popFrontObjOneDervOne() {
    if (0 == objOneDervOne.size())
      return null;
    return this.objOneDervOne.remove(0);
  }

  public void pushBackObjOneDervOne(RefClassOne value) {
    this.objOneDervOne.add(value);
  }

  public Iterator iteratorObjOneDervOne() {
    return this.objOneDervOne.iterator();
  }

  protected HashMap<String, RefClassTwo> mapOneDervOne = new HashMap<>();

  public int sizeOfMapOneDervOne() {
    return this.mapOneDervOne.size();
  }

  public RefClassTwo getMapOneDervOne(String key) {
    return this.mapOneDervOne.get(key);
  }

  public void putMapOneDervOne(String key, RefClassTwo value) {
    this.mapOneDervOne.put(key, value);
  }

  public RefClassTwo removeMapOneDervOne(String key) {
    return this.mapOneDervOne.remove(key);
  }

  public Set<String> keySetMapOneDervOne() {
    return this.mapOneDervOne.keySet();
  }

}

********

package dev.riotjy.testProject;

import java.util.HashMap;
import java.util.Set;

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

  protected ExternalClassType objOneDervTwo;

  public ExternalClassType getObjOneDervTwo() {
    return this.objOneDervTwo;
  }

  public void setObjOneDervTwo(ExternalClassType objOneDervTwo) {
    this.objOneDervTwo = objOneDervTwo;
  }

  protected HashMap<String, ExternalClassType4Coll> mapOneDervTwo = new HashMap<>();

  public int sizeOfMapOneDervTwo() {
    return this.mapOneDervTwo.size();
  }

  public ExternalClassType4Coll getMapOneDervTwo(String key) {
    return this.mapOneDervTwo.get(key);
  }

  public void putMapOneDervTwo(String key, ExternalClassType4Coll value) {
    this.mapOneDervTwo.put(key, value);
  }

  public ExternalClassType4Coll removeMapOneDervTwo(String key) {
    return this.mapOneDervTwo.remove(key);
  }

  public Set<String> keySetMapOneDervTwo() {
    return this.mapOneDervTwo.keySet();
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


********


[main] INFO dev.riotjy.mobjy.export.ModelExporter - 
********
package dev.riotjy.testProject;


import dev.riotjy.demo.extclass.ExtClassJ;


public  class ExternalClassType extends ExtClassJ {
}

********

package dev.riotjy.testProject;


import dev.riotjy.demo.extclass.ExtClass4CollJ;


public  class ExternalClassType4Coll extends ExtClass4CollJ {
}

********

package dev.riotjy.testProject;

public enum MjyPrimitiveType {
  BOOLEAN,
  INT,
  FLOAT,
  DOUBLE,
  STRING,
  INVALID;
}

********

package dev.riotjy.testProject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;

import java.util.Iterator;
import dev.riotjy.demo.extclass.ExtClassJ;
import dev.riotjy.demo.extclass.ExtClass4CollJ;


public  class TestProjectSerializer {
  private static String serBaseClass(BaseClass value) {
    return
        lin(con(qtd("boolOneBase"), serValue(value.boolOneBase))) +
        lin(con(qtd("intOneBase"), serValue(value.intOneBase))) +
        lin(con(qtd("intTwoBase"), serValue(value.intTwoBase))) +
        lin(con(qtd("strOneBase"), serValue(value.strOneBase))) +
        lin(con(qtd("flOneBase"), serValue(value.flOneBase))) +
        lin(con(qtd("dblOneBase"), serValue(value.dblOneBase))) +
        lin(con(qtd("objOneBase"), serValue(value.objOneBase))) +
        lin(con(qtd("boolArrOne"), serArr(value.boolArrOne))) +
        lin(con(qtd("intArrOne"), serArr(value.intArrOne))) +
        lin(con(qtd("strArrOne"), serArr(value.strArrOne))) +
        lin(con(qtd("flArrBase"), serArr(value.flArrBase))) +
        lin(con(qtd("dblArrOne"), serArr(value.dblArrOne))) +
        lin(con(qtd("objArrTwoBase"), serArr(value.objArrTwoBase))) +
        con(qtd("objMapThreeBase"), serMap(value.objMapThreeBase));
  }

  private static String serDervClassOne(DervClassOne value) {
    return
        lin(serBaseClass(value)) +
        lin(con(qtd("objOneDervOne"), serArr(value.objOneDervOne))) +
        con(qtd("mapOneDervOne"), serMap(value.mapOneDervOne));
  }

  private static String serDervClassTwo(DervClassTwo value) {
    return
        lin(serDervClassOne(value)) +
        lin(con(qtd("intOneDervTwo"), serValue(value.intOneDervTwo))) +
        lin(con(qtd("strOneDervTwo"), serValue(value.strOneDervTwo))) +
        lin(con(qtd("objOneDervTwo"), serValue(value.objOneDervTwo))) +
        con(qtd("mapOneDervTwo"), serMap(value.mapOneDervTwo));
  }

  private static String serRefClassOne(RefClassOne value) {
    return
        lin(con(qtd("intOneRefOne"), serValue(value.intOneRefOne))) +
        con(qtd("strOneRefOne"), serValue(value.strOneRefOne));
  }

  private static String serRefClassTwo(RefClassTwo value) {
    return
        lin(con(qtd("intOneRefTwo"), serValue(value.intOneRefTwo))) +
        con(qtd("strOneRefTwo"), serValue(value.strOneRefTwo));
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
    String code = "{\"cnid\":" + qtd(className) + "";
    if (null != fields && !fields.isEmpty()) {
      code += "," + fields;
    }
    code += "}";
    return code;
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

********

package dev.riotjy.testProject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;

import java.util.Map.Entry;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import dev.riotjy.demo.extclass.ExtClassJ;
import dev.riotjy.demo.extclass.ExtClass4CollJ;


public  class TestProjectDeserializer {
  private static void deserBaseClass(JsonObject jo,BaseClass value) {
    value.strOneBase = jo.get("strOneBase").getAsString();
    value.boolOneBase = jo.get("boolOneBase").getAsBoolean();
    value.flOneBase = jo.get("flOneBase").getAsFloat();
    value.intOneBase = jo.get("intOneBase").getAsInt();
    value.objOneBase = (RefClassOne)deserObject(jo.get("objOneBase").getAsJsonObject());
    value.dblOneBase = jo.get("dblOneBase").getAsDouble();
    value.intTwoBase = jo.get("intTwoBase").getAsInt();
    value.objArrTwoBase = deserArr(jo.get("objArrTwoBase").getAsJsonArray(), null);
    value.strArrOne = deserArr(jo.get("strArrOne").getAsJsonArray(), MjyPrimitiveType.STRING);
    value.dblArrOne = deserArr(jo.get("dblArrOne").getAsJsonArray(), MjyPrimitiveType.DOUBLE);
    value.intArrOne = deserArr(jo.get("intArrOne").getAsJsonArray(), MjyPrimitiveType.INT);
    value.flArrBase = deserArr(jo.get("flArrBase").getAsJsonArray(), MjyPrimitiveType.FLOAT);
    value.boolArrOne = deserArr(jo.get("boolArrOne").getAsJsonArray(), MjyPrimitiveType.BOOLEAN);
    value.objMapThreeBase = deserMap(jo.get("objMapThreeBase").getAsJsonObject(), null);
  }

  private static void deserDervClassOne(JsonObject jo,DervClassOne value) {
    deserBaseClass(jo, value);
    value.objOneDervOne = deserArr(jo.get("objOneDervOne").getAsJsonArray(), null);
    value.mapOneDervOne = deserMap(jo.get("mapOneDervOne").getAsJsonObject(), null);
  }

  private static void deserDervClassTwo(JsonObject jo,DervClassTwo value) {
    deserDervClassOne(jo, value);
    value.objOneDervTwo = (ExternalClassType)deserObject(jo.get("objOneDervTwo").getAsJsonObject());
    value.intOneDervTwo = jo.get("intOneDervTwo").getAsInt();
    value.strOneDervTwo = jo.get("strOneDervTwo").getAsString();
    value.mapOneDervTwo = deserMap(jo.get("mapOneDervTwo").getAsJsonObject(), null);
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
    case "BaseClass": {
      BaseClass obj = new BaseClass();
      deserBaseClass(jo ,obj);
      return obj; }
    case "DervClassOne": {
      DervClassOne obj = new DervClassOne();
      deserDervClassOne(jo ,obj);
      return obj; }
    case "DervClassTwo": {
      DervClassTwo obj = new DervClassTwo();
      deserDervClassTwo(jo ,obj);
      return obj; }
    case "RefClassOne": {
      RefClassOne obj = new RefClassOne();
      deserRefClassOne(jo ,obj);
      return obj; }
    case "RefClassTwo": {
      RefClassTwo obj = new RefClassTwo();
      deserRefClassTwo(jo ,obj);
      return obj; }
    case "ExternalClassType": {
      ExternalClassType obj = new ExternalClassType();
      deserExternalClassType(jo ,obj);
      return obj; }
    case "ExternalClassType4Coll": {
      ExternalClassType4Coll obj = new ExternalClassType4Coll();
      deserExternalClassType4Coll(jo ,obj);
      return obj; }
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
class IMjyRoot {
public:
  virtual ~IMjyRoot() {}
  virtual std::string className() = 0;
};
#pragma once
#ifndef IMjyRoot_hpp
#define IMjyRoot_hpp

#endif // IMjyRoot_hpp

+++++++++++

#include <cstdint>
#include <string>
#include <stdexcept>
#include <vector>
#include <map>
#include <memory>
#include "RefClassOne.hpp"
#include "RefClassTwo.hpp"
#include "IMjyRoot.hpp"


#pragma once
#ifndef BaseClass_hpp
#define BaseClass_hpp

namespace testproject {

class BaseClass: public IMjyRoot {
public:
  virtual std::string className() {return "BaseClass";}

public:
  bool boolOneBase;
public:
  bool getBoolOneBase() {
    return this->boolOneBase;
  }

  void setBoolOneBase(bool boolOneBase) {
    this->boolOneBase = boolOneBase;
  }

public:
  uint64_t intOneBase;
public:
  uint64_t getIntOneBase() {
    return this->intOneBase;
  }

  void setIntOneBase(uint64_t intOneBase) {
    this->intOneBase = intOneBase;
  }

public:
  uint64_t intTwoBase;
public:
  uint64_t getIntTwoBase() {
    return this->intTwoBase;
  }

  void setIntTwoBase(uint64_t intTwoBase) {
    this->intTwoBase = intTwoBase;
  }

public:
  std::string strOneBase;
public:
  std::string getStrOneBase() {
    return this->strOneBase;
  }

  void setStrOneBase(std::string strOneBase) {
    this->strOneBase = strOneBase;
  }

public:
  float flOneBase;
public:
  float getFlOneBase() {
    return this->flOneBase;
  }

  void setFlOneBase(float flOneBase) {
    this->flOneBase = flOneBase;
  }

public:
  double dblOneBase;
public:
  double getDblOneBase() {
    return this->dblOneBase;
  }

  void setDblOneBase(double dblOneBase) {
    this->dblOneBase = dblOneBase;
  }

public:
  std::shared_ptr<RefClassOne> objOneBase = nullptr;
public:
  std::shared_ptr<RefClassOne> getObjOneBase() {
    return this->objOneBase;
  }

  void setObjOneBase(std::shared_ptr<RefClassOne> objOneBase) {
    this->objOneBase = objOneBase;
  }

public:
  std::vector<bool> boolArrOne;

public:
  std::vector<bool>::size_type sizeBoolArrOne() {
    return this->boolArrOne.size();
  }

  bool atBoolArrOne(std::vector<bool>::size_type pos) {
    if (pos >= boolArrOne.size())
      throw std::invalid_argument("Vector position/index out of bounds!");
    return this->boolArrOne.at(pos);
  }

  bool popFrontBoolArrOne() {
    if (0 == boolArrOne.size())
      throw std::length_error("Vector is empty!");
    bool ret = this->boolArrOne.front();
    this->boolArrOne.erase(boolArrOne.begin());
    return ret;
  }

  void pushBackBoolArrOne(bool value) {
    this->boolArrOne.push_back(value);
  }

  std::vector<bool>::const_iterator iteratorBoolArrOne() {
    return this->boolArrOne.cbegin();
  }

public:
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

  uint64_t popFrontIntArrOne() {
    if (0 == intArrOne.size())
      throw std::length_error("Vector is empty!");
    uint64_t ret = this->intArrOne.front();
    this->intArrOne.erase(intArrOne.begin());
    return ret;
  }

  void pushBackIntArrOne(uint64_t value) {
    this->intArrOne.push_back(value);
  }

  std::vector<uint64_t>::const_iterator iteratorIntArrOne() {
    return this->intArrOne.cbegin();
  }

public:
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

  std::string popFrontStrArrOne() {
    if (0 == strArrOne.size())
      throw std::length_error("Vector is empty!");
    std::string ret = this->strArrOne.front();
    this->strArrOne.erase(strArrOne.begin());
    return ret;
  }

  void pushBackStrArrOne(std::string value) {
    this->strArrOne.push_back(value);
  }

  std::vector<std::string>::const_iterator iteratorStrArrOne() {
    return this->strArrOne.cbegin();
  }

public:
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

  float popFrontFlArrBase() {
    if (0 == flArrBase.size())
      throw std::length_error("Vector is empty!");
    float ret = this->flArrBase.front();
    this->flArrBase.erase(flArrBase.begin());
    return ret;
  }

  void pushBackFlArrBase(float value) {
    this->flArrBase.push_back(value);
  }

  std::vector<float>::const_iterator iteratorFlArrBase() {
    return this->flArrBase.cbegin();
  }

public:
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

  double popFrontDblArrOne() {
    if (0 == dblArrOne.size())
      throw std::length_error("Vector is empty!");
    double ret = this->dblArrOne.front();
    this->dblArrOne.erase(dblArrOne.begin());
    return ret;
  }

  void pushBackDblArrOne(double value) {
    this->dblArrOne.push_back(value);
  }

  std::vector<double>::const_iterator iteratorDblArrOne() {
    return this->dblArrOne.cbegin();
  }

public:
  std::vector<std::shared_ptr<RefClassTwo>> objArrTwoBase;

public:
  std::vector<std::shared_ptr<RefClassTwo>>::size_type sizeObjArrTwoBase() {
    return this->objArrTwoBase.size();
  }

  std::shared_ptr<RefClassTwo> & atObjArrTwoBase(std::vector<std::shared_ptr<RefClassTwo>>::size_type pos) {
    if (pos >= objArrTwoBase.size())
      throw std::invalid_argument("Vector position/index out of bounds!");
    return this->objArrTwoBase.at(pos);
  }

  std::shared_ptr<RefClassTwo> popFrontObjArrTwoBase() {
    if (0 == objArrTwoBase.size())
      throw std::length_error("Vector is empty!");
    std::shared_ptr<RefClassTwo> ret = this->objArrTwoBase.front();
    this->objArrTwoBase.erase(objArrTwoBase.begin());
    return ret;
  }

  void pushBackObjArrTwoBase(std::shared_ptr<RefClassTwo> value) {
    this->objArrTwoBase.push_back(value);
  }

  std::vector<std::shared_ptr<RefClassTwo>>::const_iterator iteratorObjArrTwoBase() {
    return this->objArrTwoBase.cbegin();
  }

public:
  std::map<std::string, std::shared_ptr<RefClassTwo>> objMapThreeBase;

public:
  std::map<std::string, std::shared_ptr<RefClassTwo>>::size_type sizeObjMapThreeBase() {
    return this->objMapThreeBase.size();
  }

  std::shared_ptr<RefClassTwo> & getObjMapThreeBase(std::string key) {
    return this->objMapThreeBase[key];
  }

  bool putObjMapThreeBase(std::string key, std::shared_ptr<RefClassTwo> value) {
    auto retPair = this->objMapThreeBase.emplace(key, value);
    return retPair.second;
  }

  std::map<std::string, std::shared_ptr<RefClassTwo>>::size_type eraseObjMapThreeBase(std::string key) {
    return this->objMapThreeBase.erase(key);
  }

  std::map<std::string, std::shared_ptr<RefClassTwo>>::const_iterator iteratorObjMapThreeBase() {
    return this->objMapThreeBase.cbegin();
  }

}; // class BaseClass


} // namespace testproject
#endif // BaseClass_hpp

+++++++++++

#include <cstdint>
#include <string>
#include <stdexcept>
#include <vector>
#include <map>
#include <memory>
#include "RefClassOne.hpp"
#include "RefClassTwo.hpp"
#include "BaseClass.hpp"


#pragma once
#ifndef DervClassOne_hpp
#define DervClassOne_hpp

namespace testproject {

class DervClassOne: public BaseClass {
public:
  virtual std::string className() {return "DervClassOne";}

public:
  std::vector<std::shared_ptr<RefClassOne>> objOneDervOne;

public:
  std::vector<std::shared_ptr<RefClassOne>>::size_type sizeObjOneDervOne() {
    return this->objOneDervOne.size();
  }

  std::shared_ptr<RefClassOne> & atObjOneDervOne(std::vector<std::shared_ptr<RefClassOne>>::size_type pos) {
    if (pos >= objOneDervOne.size())
      throw std::invalid_argument("Vector position/index out of bounds!");
    return this->objOneDervOne.at(pos);
  }

  std::shared_ptr<RefClassOne> popFrontObjOneDervOne() {
    if (0 == objOneDervOne.size())
      throw std::length_error("Vector is empty!");
    std::shared_ptr<RefClassOne> ret = this->objOneDervOne.front();
    this->objOneDervOne.erase(objOneDervOne.begin());
    return ret;
  }

  void pushBackObjOneDervOne(std::shared_ptr<RefClassOne> value) {
    this->objOneDervOne.push_back(value);
  }

  std::vector<std::shared_ptr<RefClassOne>>::const_iterator iteratorObjOneDervOne() {
    return this->objOneDervOne.cbegin();
  }

public:
  std::map<std::string, std::shared_ptr<RefClassTwo>> mapOneDervOne;

public:
  std::map<std::string, std::shared_ptr<RefClassTwo>>::size_type sizeMapOneDervOne() {
    return this->mapOneDervOne.size();
  }

  std::shared_ptr<RefClassTwo> & getMapOneDervOne(std::string key) {
    return this->mapOneDervOne[key];
  }

  bool putMapOneDervOne(std::string key, std::shared_ptr<RefClassTwo> value) {
    auto retPair = this->mapOneDervOne.emplace(key, value);
    return retPair.second;
  }

  std::map<std::string, std::shared_ptr<RefClassTwo>>::size_type eraseMapOneDervOne(std::string key) {
    return this->mapOneDervOne.erase(key);
  }

  std::map<std::string, std::shared_ptr<RefClassTwo>>::const_iterator iteratorMapOneDervOne() {
    return this->mapOneDervOne.cbegin();
  }

}; // class DervClassOne


} // namespace testproject
#endif // DervClassOne_hpp

+++++++++++

#include <cstdint>
#include <string>
#include <stdexcept>
#include <map>
#include <memory>
#include "ExternalClassType.hpp"
#include "ExternalClassType4Coll.hpp"
#include "DervClassOne.hpp"


#pragma once
#ifndef DervClassTwo_hpp
#define DervClassTwo_hpp

namespace testproject {

class DervClassTwo: public DervClassOne {
public:
  virtual std::string className() {return "DervClassTwo";}

public:
  uint64_t intOneDervTwo;
public:
  uint64_t getIntOneDervTwo() {
    return this->intOneDervTwo;
  }

  void setIntOneDervTwo(uint64_t intOneDervTwo) {
    this->intOneDervTwo = intOneDervTwo;
  }

public:
  std::string strOneDervTwo;
public:
  std::string getStrOneDervTwo() {
    return this->strOneDervTwo;
  }

  void setStrOneDervTwo(std::string strOneDervTwo) {
    this->strOneDervTwo = strOneDervTwo;
  }

public:
  std::shared_ptr<ExternalClassType> objOneDervTwo = nullptr;
public:
  std::shared_ptr<ExternalClassType> getObjOneDervTwo() {
    return this->objOneDervTwo;
  }

  void setObjOneDervTwo(std::shared_ptr<ExternalClassType> objOneDervTwo) {
    this->objOneDervTwo = objOneDervTwo;
  }

public:
  std::map<std::string, std::shared_ptr<ExternalClassType4Coll>> mapOneDervTwo;

public:
  std::map<std::string, std::shared_ptr<ExternalClassType4Coll>>::size_type sizeMapOneDervTwo() {
    return this->mapOneDervTwo.size();
  }

  std::shared_ptr<ExternalClassType4Coll> & getMapOneDervTwo(std::string key) {
    return this->mapOneDervTwo[key];
  }

  bool putMapOneDervTwo(std::string key, std::shared_ptr<ExternalClassType4Coll> value) {
    auto retPair = this->mapOneDervTwo.emplace(key, value);
    return retPair.second;
  }

  std::map<std::string, std::shared_ptr<ExternalClassType4Coll>>::size_type eraseMapOneDervTwo(std::string key) {
    return this->mapOneDervTwo.erase(key);
  }

  std::map<std::string, std::shared_ptr<ExternalClassType4Coll>>::const_iterator iteratorMapOneDervTwo() {
    return this->mapOneDervTwo.cbegin();
  }

}; // class DervClassTwo


} // namespace testproject
#endif // DervClassTwo_hpp

+++++++++++

#include <cstdint>
#include <string>
#include "IMjyRoot.hpp"


#pragma once
#ifndef RefClassOne_hpp
#define RefClassOne_hpp

namespace testproject {

class RefClassOne: public IMjyRoot {
public:
  virtual std::string className() {return "RefClassOne";}

public:
  uint64_t intOneRefOne;
public:
  uint64_t getIntOneRefOne() {
    return this->intOneRefOne;
  }

  void setIntOneRefOne(uint64_t intOneRefOne) {
    this->intOneRefOne = intOneRefOne;
  }

public:
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
#include "IMjyRoot.hpp"


#pragma once
#ifndef RefClassTwo_hpp
#define RefClassTwo_hpp

namespace testproject {

class RefClassTwo: public IMjyRoot {
public:
  virtual std::string className() {return "RefClassTwo";}

public:
  uint64_t intOneRefTwo;
public:
  uint64_t getIntOneRefTwo() {
    return this->intOneRefTwo;
  }

  void setIntOneRefTwo(uint64_t intOneRefTwo) {
    this->intOneRefTwo = intOneRefTwo;
  }

public:
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

+++++++++++

#include <cstdint>
#include <string>
#include "ext/ExtClassCpp.hpp"


#pragma once
#ifndef ExternalClassType_hpp
#define ExternalClassType_hpp

namespace testproject {

class ExternalClassType : public IMjyRoot, ExtClassCpp {
public:
  virtual std::string className() {return "ExternalClassType";}

  // TODO: ADD ANY NECESSARY CODE FOR DE-/SERIALIZATION OF ExtClassCpp

}; // class ExternalClassType


} // namespace testproject
#endif // ExternalClassType_hpp

+++++++++++

#include <cstdint>
#include <string>
#include "ext/ExtClass4CollCpp.hpp"


#pragma once
#ifndef ExternalClassType4Coll_hpp
#define ExternalClassType4Coll_hpp

namespace testproject {

class ExternalClassType4Coll : public IMjyRoot, ExtClass4CollCpp {
public:
  virtual std::string className() {return "ExternalClassType4Coll";}

  // TODO: ADD ANY NECESSARY CODE FOR DE-/SERIALIZATION OF ExtClass4CollCpp

}; // class ExternalClassType4Coll


} // namespace testproject
#endif // ExternalClassType4Coll_hpp

+++++++++++

#include <cstdint>
#include <string>
#include <stdexcept>
#include <vector>
#include <map>
#include <iterator>
#include "json.hpp"
#include "BaseClass.hpp"
#include "DervClassOne.hpp"
#include "DervClassTwo.hpp"
#include "RefClassOne.hpp"
#include "RefClassTwo.hpp"
#include "ExternalClassType.hpp"
#include "ExternalClassType4Coll.hpp"


#pragma once
#ifndef testProjectSerializer_hpp
#define testProjectSerializer_hpp

namespace testproject {

class TestProjectSerializer: public IMjyRoot {
public:
  virtual std::string className() {return "TestProjectSerializer";}

private:
  std::string serBaseClass(BaseClass * value) {
    return
        lin(con(qtd("strOneBase"), qtd(value->strOneBase))) +
        lin(con(qtd("boolOneBase"), serBool(value->boolOneBase))) +
        lin(con(qtd("flOneBase"), std::to_string(value->flOneBase))) +
        lin(con(qtd("intOneBase"), std::to_string(value->intOneBase))) +
        lin(con(qtd("objOneBase"), serCValue(value->objOneBase.get()))) +
        lin(con(qtd("dblOneBase"), std::to_string(value->dblOneBase))) +
        lin(con(qtd("intTwoBase"), std::to_string(value->intTwoBase))) +
        lin(con(qtd("objArrTwoBase"), serArr(value->objArrTwoBase))) +
        lin(con(qtd("strArrOne"), serArr(value->strArrOne))) +
        lin(con(qtd("dblArrOne"), serArr(value->dblArrOne))) +
        lin(con(qtd("intArrOne"), serArr(value->intArrOne))) +
        lin(con(qtd("flArrBase"), serArr(value->flArrBase))) +
        lin(con(qtd("boolArrOne"), serArr(value->boolArrOne))) +
        con(qtd("objMapThreeBase"), serMap(value->objMapThreeBase));
  }

private:
  std::string serDervClassOne(DervClassOne * value) {
    return
        lin(serBaseClass(value)) +
        lin(con(qtd("objOneDervOne"), serArr(value->objOneDervOne))) +
        con(qtd("mapOneDervOne"), serMap(value->mapOneDervOne));
  }

private:
  std::string serDervClassTwo(DervClassTwo * value) {
    return
        lin(serDervClassOne(value)) +
        lin(con(qtd("objOneDervTwo"), serCValue(value->objOneDervTwo.get()))) +
        lin(con(qtd("intOneDervTwo"), std::to_string(value->intOneDervTwo))) +
        lin(con(qtd("strOneDervTwo"), qtd(value->strOneDervTwo))) +
        con(qtd("mapOneDervTwo"), serMap(value->mapOneDervTwo));
  }

private:
  std::string serRefClassOne(RefClassOne * value) {
    return
        lin(con(qtd("intOneRefOne"), std::to_string(value->intOneRefOne))) +
        con(qtd("strOneRefOne"), qtd(value->strOneRefOne));
  }

private:
  std::string serRefClassTwo(RefClassTwo * value) {
    return
        lin(con(qtd("intOneRefTwo"), std::to_string(value->intOneRefTwo))) +
        con(qtd("strOneRefTwo"), qtd(value->strOneRefTwo));
  }

private:
  std::string serExternalClassType(ExternalClassType * value) {
    return
        // TODO: REPLACE WITH SERIALIZATION CODE FOR CLASS ExternalClassType AS NECESSARY! VERIFY CLASS DEFINITION.
        "";
  }

private:
  std::string serExternalClassType4Coll(ExternalClassType4Coll * value) {
    return
        // TODO: REPLACE WITH SERIALIZATION CODE FOR CLASS ExternalClassType4Coll AS NECESSARY! VERIFY CLASS DEFINITION.
        "";
  }

private:
  std::string serCValue(IMjyRoot* val) {
    if(val->className() == "BaseClass") {
        return serClass(val->className(), serBaseClass(dynamic_cast<BaseClass*>(val)));
    }
    if(val->className() == "DervClassOne") {
        return serClass(val->className(), serDervClassOne(dynamic_cast<DervClassOne*>(val)));
    }
    if(val->className() == "DervClassTwo") {
        return serClass(val->className(), serDervClassTwo(dynamic_cast<DervClassTwo*>(val)));
    }
    if(val->className() == "RefClassOne") {
        return serClass(val->className(), serRefClassOne(dynamic_cast<RefClassOne*>(val)));
    }
    if(val->className() == "RefClassTwo") {
        return serClass(val->className(), serRefClassTwo(dynamic_cast<RefClassTwo*>(val)));
    }
    if(val->className() == "ExternalClassType") {
        return serClass(val->className(), serExternalClassType(dynamic_cast<ExternalClassType*>(val)));
    }
    if(val->className() == "ExternalClassType4Coll") {
        return serClass(val->className(), serExternalClassType4Coll(dynamic_cast<ExternalClassType4Coll*>(val)));
    }
    return "";  }

private:
  std::string qtd(std::string in) {
    return "\"" + in + "\"";
  }
  std::string con(std::string first, std::string second) {
    return first + ":" + second;
  }
  std::string lin(std::string in) {
    return in + ",";
  }
  std::string crl(std::string in) {
    return "{" + in + "}";
  }
  
  std::string serClass(std::string className, std::string fields) {
    std::string json =  "{\"cnid\":\"" + className + "\"";
    if (!fields.empty()) {
      json += "," + fields;
    }
    json += "}";
    return json;
  }
  
  std::string serBool(bool val) {
    return val ? "true" : "false";
  }

  template <typename T>
  std::string serArr(std::vector<std::shared_ptr<T>> arr) {
    std::string json = "[";
    typename std::vector<std::shared_ptr<T>>::const_iterator it = arr.cbegin();
  
    while (it != arr.cend()) {
      if (it != --arr.cend()) {
        json += lin(serCValue(it->get()));
      } else {
        json += serCValue(it->get());
      }
      ++it;
    }
  
    json += "]";
    return json;
  }
  
  template <typename T>
  std::string serArr(std::vector<T> arr) {
  
    int arrSize = arr.size();
    std::string json = "[";
  
    for (int i = 0; i < arrSize; ++i) {
      T val = arr[i];
      if (i < arrSize - 1) {
        json += lin(std::to_string(val));
      } else {
        json += std::to_string(val);
      }
    }
  
    json += "]";
    return json;
  }

  std::string serArr(std::vector<bool> arr) {

    int arrSize = arr.size();
    std::string json = "[";

    for (int i = 0; i < arrSize; ++i) {
      bool val = arr[i];
      if (i < arrSize - 1) {
        json += lin(serBool(val));
      } else {
        json += serBool(val);
      }
    }

    json += "]";
    return json;
  }
  
  std::string serArr(std::vector<std::string> arr) {
  
    int arrSize = arr.size();
    std::string json = "[";
  
    for (int i = 0; i < arrSize; ++i) {
      std::string val = arr[i];
      if (i < arrSize - 1) {
        json += lin(qtd(val));
      } else {
        json += qtd(val);
      }
    }
  
    json += "]";
    return json;
  }
  
  template <typename T>
  std::string serMap(std::map<std::string, std::shared_ptr<T>> hm) {
    std::string json = "{";
  
    typename std::map<std::string, std::shared_ptr<T>>::const_iterator it = hm.cbegin();
  
    while (it != hm.cend()) {
      if (it != --hm.cend()) {
        json += lin(con(qtd(it->first), serCValue(it->second.get())));
      } else {
        json += con(qtd(it->first), serCValue(it->second.get()));
      }
      ++it;
    }
  
    json += "}";
    return json;
  }
  
  
  template <typename T>
  std::string serMap(std::map<std::string, T> map) {
    std::string json = "{";
  
    typename std::map<std::string, T>::const_iterator it = map.cbegin();
  
    while (it != map.cend()) {
      if (it != --map.cend()) {
        json += lin(con(qtd(it->first), std::to_string(it->second)));
      } else {
        json += con(qtd(it->first), std::to_string(it->second));
      }
      ++it;
    }
  
    json += "}";
    return json;
  }
  
  std::string serMap(std::map<std::string, bool> map) {
    std::string json = "{";

    std::map<std::string, bool>::const_iterator it = map.cbegin();

    while (it != map.cend()) {
      if (it != --map.cend()) {
        json += lin(con(qtd(it->first), serBool(it->second)));
      } else {
        json += con(qtd(it->first), serBool(it->second));
      }
      ++it;
    }
  }

  std::string serMap(std::map<std::string, std::string> map) {
    std::string json = "{";
  
    std::map<std::string, std::string>::const_iterator it = map.cbegin();
  
    while (it != map.cend()) {
      if (it != --map.cend()) {
        json += lin(con(qtd(it->first), qtd(it->second)));
      } else {
        json += con(qtd(it->first), qtd(it->second));
      }
      ++it;
    }
  
    json += "}";
    return json;
  }
public:
  std::string serialize(IMjyRoot& val) {
    return serCValue(&val);
  }

}; // class TestProjectSerializer


} // namespace testproject
#endif // testProjectSerializer_hpp

+++++++++++

#include <cstdint>
#include <string>
#include <stdexcept>
#include <vector>
#include <map>
#include <iterator>
#include <memory>
#include "json.hpp"
#include "BaseClass.hpp"
#include "DervClassOne.hpp"
#include "DervClassTwo.hpp"
#include "RefClassOne.hpp"
#include "RefClassTwo.hpp"
#include "ExternalClassType.hpp"
#include "ExternalClassType4Coll.hpp"


#pragma once
#ifndef testProjectDeserializer_hpp
#define testProjectDeserializer_hpp

namespace testproject {

class TestProjectDeserializer: public IMjyRoot {
public:
  virtual std::string className() {return "TestProjectDeserializer";}

  void deserBaseClass(jsonl::json & jo,BaseClass * value) {
    value->strOneBase = jo["strOneBase"];
    value->boolOneBase = jo["boolOneBase"];
    value->flOneBase = jo["flOneBase"];
    value->intOneBase = jo["intOneBase"];
    value->objOneBase = std::dynamic_pointer_cast<RefClassOne>(deserObject(jo["objOneBase"]));
    value->dblOneBase = jo["dblOneBase"];
    value->intTwoBase = jo["intTwoBase"];
    deserArr(jo["objArrTwoBase"], value->objArrTwoBase);
    deserArr(jo["strArrOne"], value->strArrOne);
    deserArr(jo["dblArrOne"], value->dblArrOne);
    deserArr(jo["intArrOne"], value->intArrOne);
    deserArr(jo["flArrBase"], value->flArrBase);
    deserArr(jo["boolArrOne"], value->boolArrOne);
    deserMap(jo["objMapThreeBase"], value->objMapThreeBase);
  }

  void deserDervClassOne(jsonl::json & jo,DervClassOne * value) {
    deserBaseClass(jo, value);
    deserArr(jo["objOneDervOne"], value->objOneDervOne);
    deserMap(jo["mapOneDervOne"], value->mapOneDervOne);
  }

  void deserDervClassTwo(jsonl::json & jo,DervClassTwo * value) {
    deserDervClassOne(jo, value);
    value->objOneDervTwo = std::dynamic_pointer_cast<ExternalClassType>(deserObject(jo["objOneDervTwo"]));
    value->intOneDervTwo = jo["intOneDervTwo"];
    value->strOneDervTwo = jo["strOneDervTwo"];
    deserMap(jo["mapOneDervTwo"], value->mapOneDervTwo);
  }

  void deserRefClassOne(jsonl::json & jo,RefClassOne * value) {
    value->intOneRefOne = jo["intOneRefOne"];
    value->strOneRefOne = jo["strOneRefOne"];
  }

  void deserRefClassTwo(jsonl::json & jo,RefClassTwo * value) {
    value->intOneRefTwo = jo["intOneRefTwo"];
    value->strOneRefTwo = jo["strOneRefTwo"];
  }

  void deserExternalClassType(jsonl::json & jo,ExternalClassType * value) {
        // TODO: REPLACE WITH SERIALIZATION CODE FOR CLASS ExternalClassType AS NECESSARY! VERIFY CLASS DEFINITION.
  }

  void deserExternalClassType4Coll(jsonl::json & jo,ExternalClassType4Coll * value) {
        // TODO: REPLACE WITH SERIALIZATION CODE FOR CLASS ExternalClassType4Coll AS NECESSARY! VERIFY CLASS DEFINITION.
  }

  std::shared_ptr<IMjyRoot> deserObject(jsonl::json &  jo) {
    std::string cnid = jo["cnid"];

    if (cnid == "BaseClass") {
      std::shared_ptr<BaseClass> obj = std::make_shared<BaseClass>();
      deserBaseClass(jo, obj.get());
      return obj;
    }
    if (cnid == "DervClassOne") {
      std::shared_ptr<DervClassOne> obj = std::make_shared<DervClassOne>();
      deserDervClassOne(jo, obj.get());
      return obj;
    }
    if (cnid == "DervClassTwo") {
      std::shared_ptr<DervClassTwo> obj = std::make_shared<DervClassTwo>();
      deserDervClassTwo(jo, obj.get());
      return obj;
    }
    if (cnid == "RefClassOne") {
      std::shared_ptr<RefClassOne> obj = std::make_shared<RefClassOne>();
      deserRefClassOne(jo, obj.get());
      return obj;
    }
    if (cnid == "RefClassTwo") {
      std::shared_ptr<RefClassTwo> obj = std::make_shared<RefClassTwo>();
      deserRefClassTwo(jo, obj.get());
      return obj;
    }
    if (cnid == "ExternalClassType") {
      std::shared_ptr<ExternalClassType> obj = std::make_shared<ExternalClassType>();
      deserExternalClassType(jo, obj.get());
      return obj;
    }
    if (cnid == "ExternalClassType4Coll") {
      std::shared_ptr<ExternalClassType4Coll> obj = std::make_shared<ExternalClassType4Coll>();
      deserExternalClassType4Coll(jo, obj.get());
      return obj;
    }
    return nullptr;
  }


  template <typename T>
  void deserArr(jsonl::json jo, std::vector<T> & vec) {
    std::size_t size = jo.size();
    vec.clear();
    for (std::size_t i = 0; i < size; ++i) {
      vec.push_back(jo[i]);
    }
  }

  template <typename T>
  void deserArr(jsonl::json jo, std::vector<std::shared_ptr<T>> & vec) {
    std::size_t size = jo.size();
    vec.clear();
    for (std::size_t i = 0; i < size; ++i) {
      vec.push_back(std::dynamic_pointer_cast<T>(deserObject(jo[i])));
    }
  }

  template <typename T>
  void deserMap(jsonl::json jo, std::map<std::string, T> & map) {
    map.clear();
    auto it = jo.cbegin();
    while (it != jo.cend()) {
      map.emplace(it.key(), it.value());
      ++it;
    }
  }

  template <typename T>
  void deserMap(jsonl::json jo, std::map<std::string, std::shared_ptr<T>> & map) {
    map.clear();
    auto it = jo.cbegin();
    while (it != jo.cend()) {
      map.emplace(it.key(), std::dynamic_pointer_cast<T>(deserObject(jo[it.key()])));
      ++it;
    }
  }

  std::shared_ptr<IMjyRoot> deserialize(jsonl::json &  jo) {
    return deserObject(jo);
  }
  
  std::shared_ptr<IMjyRoot> deserialize(std::string jsonStr) {
    jsonl::json jo = jsonl::json::parse(jsonStr);
    return deserialize(jo);
  }


}; // class TestProjectDeserializer


} // namespace testproject
#endif // testProjectDeserializer_hpp
```