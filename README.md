# mobjy for Java

A collection of utilities to generate java classes and converters from a JSON or YAML data model.

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
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public  class BaseClass {
  public Integer intOneBase;

  public Integer getIntOneBase() {
    return this.intOneBase;
  }

  public void setIntOneBase(Integer intOneBase) {
    this.intOneBase = intOneBase;
  }


  public Integer intTwoBase;

  public Integer getIntTwoBase() {
    return this.intTwoBase;
  }

  public void setIntTwoBase(Integer intTwoBase) {
    this.intTwoBase = intTwoBase;
  }


  public String strOneBase;

  public String getStrOneBase() {
    return this.strOneBase;
  }

  public void setStrOneBase(String strOneBase) {
    this.strOneBase = strOneBase;
  }


  public Float flOneBase;

  public Float getFlOneBase() {
    return this.flOneBase;
  }

  public void setFlOneBase(Float flOneBase) {
    this.flOneBase = flOneBase;
  }


  public RefClassOne objOneBase;

  public RefClassOne getObjOneBase() {
    return this.objOneBase;
  }

  public void setObjOneBase(RefClassOne objOneBase) {
    this.objOneBase = objOneBase;
  }


  public List<Integer> intArrOne = new ArrayList<>();

  public int sizeOfIntArrOne() {
    return this.intArrOne.size();
  }

  public Integer popFrontFromIntArrOne() {
    if (0 == intArrOne.size())
      return null;
    return this.intArrOne.remove(0);
  }

  public void pushBackToIntArrOne(Integer value) {
    this.intArrOne.add(value);
  }

  public Iterator iteratorOfIntArrOne() {
    return this.intArrOne.iterator();
  }

  public List<String> strArrOne = new ArrayList<>();

  public int sizeOfStrArrOne() {
    return this.strArrOne.size();
  }

  public String popFrontFromStrArrOne() {
    if (0 == strArrOne.size())
      return null;
    return this.strArrOne.remove(0);
  }

  public void pushBackToStrArrOne(String value) {
    this.strArrOne.add(value);
  }

  public Iterator iteratorOfStrArrOne() {
    return this.strArrOne.iterator();
  }

  public List<Float> flArrBase = new ArrayList<>();

  public int sizeOfFlArrBase() {
    return this.flArrBase.size();
  }

  public Float popFrontFromFlArrBase() {
    if (0 == flArrBase.size())
      return null;
    return this.flArrBase.remove(0);
  }

  public void pushBackToFlArrBase(Float value) {
    this.flArrBase.add(value);
  }

  public Iterator iteratorOfFlArrBase() {
    return this.flArrBase.iterator();
  }

  public List<Double> dblArrOne = new ArrayList<>();

  public int sizeOfDblArrOne() {
    return this.dblArrOne.size();
  }

  public Double popFrontFromDblArrOne() {
    if (0 == dblArrOne.size())
      return null;
    return this.dblArrOne.remove(0);
  }

  public void pushBackToDblArrOne(Double value) {
    this.dblArrOne.add(value);
  }

  public Iterator iteratorOfDblArrOne() {
    return this.dblArrOne.iterator();
  }

  public List<RefClassTwo> objArrTwoBase = new ArrayList<>();

  public int sizeOfObjArrTwoBase() {
    return this.objArrTwoBase.size();
  }

  public RefClassTwo popFrontFromObjArrTwoBase() {
    if (0 == objArrTwoBase.size())
      return null;
    return this.objArrTwoBase.remove(0);
  }

  public void pushBackToObjArrTwoBase(RefClassTwo value) {
    this.objArrTwoBase.add(value);
  }

  public Iterator iteratorOfObjArrTwoBase() {
    return this.objArrTwoBase.iterator();
  }

  public HashMap<String, RefClassTwo> objMapThreeBase = new HashMap<>();

  public int sizeOfObjMapThreeBase() {
    return this.objMapThreeBase.size();
  }

  public RefClassTwo getFromObjMapThreeBase(String key) {
    return this.objMapThreeBase.get(key);
  }

  public void putToObjMapThreeBase(String key, RefClassTwo value) {
    this.objMapThreeBase.put(key, value);
  }

  public Set<String> keySetOfObjMapThreeBase() {
    return this.objMapThreeBase.keySet();
  }

}
********
********
import java.util.Map;
import java.util.HashMap;

import dev.riotj.demo.extclass.ExtClassJ;
import dev.riotj.demo.extclass.ExtClass4CollJ;


public  class DervClassOne extends BaseClass {
  public Integer intOneDervOne;

  public Integer getIntOneDervOne() {
    return this.intOneDervOne;
  }

  public void setIntOneDervOne(Integer intOneDervOne) {
    this.intOneDervOne = intOneDervOne;
  }


  public String strOneDervOne;

  public String getStrOneDervOne() {
    return this.strOneDervOne;
  }

  public void setStrOneDervOne(String strOneDervOne) {
    this.strOneDervOne = strOneDervOne;
  }


  public ExtClassJ objOneDervOne;

  public ExtClassJ getObjOneDervOne() {
    return this.objOneDervOne;
  }

  public void setObjOneDervOne(ExtClassJ objOneDervOne) {
    this.objOneDervOne = objOneDervOne;
  }


  public HashMap<String, ExtClass4CollJ> mapOneDervOne = new HashMap<>();

  public int sizeOfMapOneDervOne() {
    return this.mapOneDervOne.size();
  }

  public ExtClass4CollJ getFromMapOneDervOne(String key) {
    return this.mapOneDervOne.get(key);
  }

  public void putToMapOneDervOne(String key, ExtClass4CollJ value) {
    this.mapOneDervOne.put(key, value);
  }

  public Set<String> keySetOfMapOneDervOne() {
    return this.mapOneDervOne.keySet();
  }

}
********
********
public  class DervClassTwo extends DervClassOne {
  public Integer intOneDervTwo;

  public Integer getIntOneDervTwo() {
    return this.intOneDervTwo;
  }

  public void setIntOneDervTwo(Integer intOneDervTwo) {
    this.intOneDervTwo = intOneDervTwo;
  }


  public String strOneDervTwo;

  public String getStrOneDervTwo() {
    return this.strOneDervTwo;
  }

  public void setStrOneDervTwo(String strOneDervTwo) {
    this.strOneDervTwo = strOneDervTwo;
  }


}
********
********
public  class RefClassOne {
  public Integer intOneRefOne;

  public Integer getIntOneRefOne() {
    return this.intOneRefOne;
  }

  public void setIntOneRefOne(Integer intOneRefOne) {
    this.intOneRefOne = intOneRefOne;
  }


  public String strOneRefOne;

  public String getStrOneRefOne() {
    return this.strOneRefOne;
  }

  public void setStrOneRefOne(String strOneRefOne) {
    this.strOneRefOne = strOneRefOne;
  }
}
********
********
public  class RefClassTwo {
  public Integer intOneRefTwo;

  public Integer getIntOneRefTwo() {
    return this.intOneRefTwo;
  }

  public void setIntOneRefTwo(Integer intOneRefTwo) {
    this.intOneRefTwo = intOneRefTwo;
  }


  public String strOneRefTwo;

  public String getStrOneRefTwo() {
    return this.strOneRefTwo;
  }

  public void setStrOneRefTwo(String strOneRefTwo) {
    this.strOneRefTwo = strOneRefTwo;
  }


}
```
and this CPP code:

```CPP
#inlcude <cstdint>
#inlcude <string>

#include <list>
#include <map>

#pragma once
#ifndef BaseClass_hpp
#define BaseClass_hpp

#namespace testProject {

class BaseClass {
public:
  uint64_t intOneBase;

  uint64_t getIntOneBase() {
    return this->intOneBase;
  }

  void setIntOneBase(uint64_t intOneBase) {
    this->intOneBase = intOneBase;
  }


public:
  uint64_t intTwoBase;

  uint64_t getIntTwoBase() {
    return this->intTwoBase;
  }

  void setIntTwoBase(uint64_t intTwoBase) {
    this->intTwoBase = intTwoBase;
  }


public:
  std::string strOneBase;

  std::string getStrOneBase() {
    return this->strOneBase;
  }

  void setStrOneBase(std::string strOneBase) {
    this->strOneBase = strOneBase;
  }


public:
  float flOneBase;

  float getFlOneBase() {
    return this->flOneBase;
  }

  void setFlOneBase(float flOneBase) {
    this->flOneBase = flOneBase;
  }


public:
  RefClassOne objOneBase;

  RefClassOne getObjOneBase() {
    return this->objOneBase;
  }

  void setObjOneBase(RefClassOne objOneBase) {
    this->objOneBase = objOneBase;
  }


public:  std::vector<uint64_t> intArrOne;

  size_type sizeOfIntArrOne() {
    return this->intArrOne.size();
  }

  uint64_t popFrontFromIntArrOne() {
    if (0 == intArrOne.size())
      return null;
    uint64_t ret = this->intArrOne.front();
    this->intArrOne.erase(intArrOne.begin());
    return ret;
  }

  void pushBackToIntArrOne(uint64_t value) {
    this->intArrOne.push_back(value);
  }

  auto iteratorOfIntArrOne() {
    return this->intArrOne.cbegin();
  }

public:  std::vector<std::string> strArrOne;

  size_type sizeOfStrArrOne() {
    return this->strArrOne.size();
  }

  std::string popFrontFromStrArrOne() {
    if (0 == strArrOne.size())
      return null;
    std::string ret = this->strArrOne.front();
    this->strArrOne.erase(strArrOne.begin());
    return ret;
  }

  void pushBackToStrArrOne(std::string value) {
    this->strArrOne.push_back(value);
  }

  auto iteratorOfStrArrOne() {
    return this->strArrOne.cbegin();
  }

public:  std::vector<float> flArrBase;

  size_type sizeOfFlArrBase() {
    return this->flArrBase.size();
  }

  float popFrontFromFlArrBase() {
    if (0 == flArrBase.size())
      return null;
    float ret = this->flArrBase.front();
    this->flArrBase.erase(flArrBase.begin());
    return ret;
  }

  void pushBackToFlArrBase(float value) {
    this->flArrBase.push_back(value);
  }

  auto iteratorOfFlArrBase() {
    return this->flArrBase.cbegin();
  }

public:  std::vector<double> dblArrOne;

  size_type sizeOfDblArrOne() {
    return this->dblArrOne.size();
  }

  double popFrontFromDblArrOne() {
    if (0 == dblArrOne.size())
      return null;
    double ret = this->dblArrOne.front();
    this->dblArrOne.erase(dblArrOne.begin());
    return ret;
  }

  void pushBackToDblArrOne(double value) {
    this->dblArrOne.push_back(value);
  }

  auto iteratorOfDblArrOne() {
    return this->dblArrOne.cbegin();
  }

public:  std::vector<RefClassTwo> objArrTwoBase;

  size_type sizeOfObjArrTwoBase() {
    return this->objArrTwoBase.size();
  }

  RefClassTwo popFrontFromObjArrTwoBase() {
    if (0 == objArrTwoBase.size())
      return null;
    RefClassTwo ret = this->objArrTwoBase.front();
    this->objArrTwoBase.erase(objArrTwoBase.begin());
    return ret;
  }

  void pushBackToObjArrTwoBase(RefClassTwo value) {
    this->objArrTwoBase.push_back(value);
  }

  auto iteratorOfObjArrTwoBase() {
    return this->objArrTwoBase.cbegin();
  }

public:
  std::map<std::string, RefClassTwo> objMapThreeBase;

  size_type sizeOfObjMapThreeBase() {
    return this->objMapThreeBase.size();
  }

  RefClassTwo getFromObjMapThreeBase(String key) {
    return this->objMapThreeBase[key];
  }

  void putToObjMapThreeBase(String key, RefClassTwo value) {
    this->objMapThreeBase.emplace(key, value);
  }

  auto iteratorOfObjMapThreeBase() {
    return this->objMapThreeBase.cbegin();
  }

}; // class BaseClass


} // namespace testProject
#endif // BaseClass_hpp
+++++++++++
+++++++++++
#inlcude <cstdint>
#inlcude <string>

#include <map>
#include <dir1/dir2/ExtClassCpp>
#include <dir1/dir2/ExtClass4CollCpp>


#pragma once
#ifndef DervClassOne_hpp
#define DervClassOne_hpp

#namespace testProject {

class DervClassOne: public BaseClass {
public:
  uint64_t intOneDervOne;

  uint64_t getIntOneDervOne() {
    return this->intOneDervOne;
  }

  void setIntOneDervOne(uint64_t intOneDervOne) {
    this->intOneDervOne = intOneDervOne;
  }


public:
  std::string strOneDervOne;

  std::string getStrOneDervOne() {
    return this->strOneDervOne;
  }

  void setStrOneDervOne(std::string strOneDervOne) {
    this->strOneDervOne = strOneDervOne;
  }


public:
  ExtClassCpp objOneDervOne;

  ExtClassCpp getObjOneDervOne() {
    return this->objOneDervOne;
  }

  void setObjOneDervOne(ExtClassCpp objOneDervOne) {
    this->objOneDervOne = objOneDervOne;
  }


public:
  std::map<std::string, ExtClass4CollCpp> mapOneDervOne;

  size_type sizeOfMapOneDervOne() {
    return this->mapOneDervOne.size();
  }

  ExtClass4CollCpp getFromMapOneDervOne(String key) {
    return this->mapOneDervOne[key];
  }

  void putToMapOneDervOne(String key, ExtClass4CollCpp value) {
    this->mapOneDervOne.emplace(key, value);
  }

  auto iteratorOfMapOneDervOne() {
    return this->mapOneDervOne.cbegin();
  }

}; // class DervClassOne


} // namespace testProject
#endif // DervClassOne_hpp
+++++++++++
+++++++++++
#inlcude <cstdint>
#inlcude <string>


#pragma once
#ifndef DervClassTwo_hpp
#define DervClassTwo_hpp

#namespace testProject {

class DervClassTwo: public DervClassOne {
public:
  uint64_t intOneDervTwo;

  uint64_t getIntOneDervTwo() {
    return this->intOneDervTwo;
  }

  void setIntOneDervTwo(uint64_t intOneDervTwo) {
    this->intOneDervTwo = intOneDervTwo;
  }


public:
  std::string strOneDervTwo;

  std::string getStrOneDervTwo() {
    return this->strOneDervTwo;
  }

  void setStrOneDervTwo(std::string strOneDervTwo) {
    this->strOneDervTwo = strOneDervTwo;
  }


}; // class DervClassTwo


} // namespace testProject
#endif // DervClassTwo_hpp
+++++++++++
+++++++++++
#inlcude <cstdint>
#inlcude <string>


#pragma once
#ifndef RefClassOne_hpp
#define RefClassOne_hpp

#namespace testProject {

class RefClassOne {
public:
  uint64_t intOneRefOne;

  uint64_t getIntOneRefOne() {
    return this->intOneRefOne;
  }

  void setIntOneRefOne(uint64_t intOneRefOne) {
    this->intOneRefOne = intOneRefOne;
  }


public:
  std::string strOneRefOne;

  std::string getStrOneRefOne() {
    return this->strOneRefOne;
  }

  void setStrOneRefOne(std::string strOneRefOne) {
    this->strOneRefOne = strOneRefOne;
  }


}; // class RefClassOne


} // namespace testProject
#endif // RefClassOne_hpp
+++++++++++
+++++++++++
#inlcude <cstdint>
#inlcude <string>


#pragma once
#ifndef RefClassTwo_hpp
#define RefClassTwo_hpp

#namespace testProject {

class RefClassTwo {
public:
  uint64_t intOneRefTwo;

  uint64_t getIntOneRefTwo() {
    return this->intOneRefTwo;
  }

  void setIntOneRefTwo(uint64_t intOneRefTwo) {
    this->intOneRefTwo = intOneRefTwo;
  }


public:
  std::string strOneRefTwo;

  std::string getStrOneRefTwo() {
    return this->strOneRefTwo;
  }

  void setStrOneRefTwo(std::string strOneRefTwo) {
    this->strOneRefTwo = strOneRefTwo;
  }


}; // class RefClassTwo


} // namespace testProject
#endif // RefClassTwo_hpp
```