/*******************************************************************************
 * Copyright 2020 riotjy
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
package dev.riotjy.mobjy.model;

public class MjyVersion {

  private int major;
  private int minor;
  private int revision;
  private int candidate;
  private VersionStatus status;
  
  MjyVersion(
      int major, int minor, int revision, 
      int candidate, VersionStatus status) {
    super();
    this.major = major;
    this.minor = minor;
    this.revision = revision;
    this.candidate = candidate;
    this.status = status;
  }

  private int toDecimalMMR() {
    return major * 10000 + minor * 100 + revision;
  }
  
  public boolean sameMaj(MjyVersion other) {
    return this.major == other.major;
  }

  public boolean sameMajMin(MjyVersion other) {
    return 
        this.major == other.major &&
        this.minor == other.minor;
  }

  public boolean sameMajMinRev(MjyVersion other) {
    return 
        this.toDecimalMMR() == other.toDecimalMMR();
  }

  public boolean sameMajMinRevStat(MjyVersion other) {
    return 
        this.toDecimalMMR() == other.toDecimalMMR() &&
        this.status == other.status;
  }

  public boolean greater(MjyVersion other) {
    if (this.toDecimalMMR() > other.toDecimalMMR())
      return true;
    if (this.status == VersionStatus.RELEASE  && other.status != VersionStatus.RELEASE)
      return true;
    if (this.status == VersionStatus.BETA  && other.status == VersionStatus.RELEASE)
      return false;
    if (this.status == VersionStatus.BETA  && other.status == VersionStatus.ALPHA)
      return true;
    if (this.status == VersionStatus.ALPHA  && other.status != VersionStatus.ALPHA)
      return false;
    if (this.candidate > other.candidate)
      return true;
    return false;
  }

  public boolean same(MjyVersion other) {
    if (this.toDecimalMMR() != other.toDecimalMMR())
      return false;
    if (this.status != other.status)
      return false;
    if (this.candidate != other.candidate)
      return false;
    return true;
  }

  public boolean lesser(MjyVersion other) {
    return !(this.greater(other) || this.same(other));
  }

  public int getMajor() {
    return major;
  }

  public void setMajor(int major) {
    this.major = major;
  }

  public int getMinor() {
    return minor;
  }

  public void setMinor(int minor) {
    this.minor = minor;
  }

  public int getRevision() {
    return revision;
  }

  public void setRevision(int revision) {
    this.revision = revision;
  }

  public int getCandidate() {
    return candidate;
  }

  public void setCandidate(int candidate) {
    this.candidate = candidate;
  }

  public VersionStatus getStatus() {
    return status;
  }

  public void setStatus(VersionStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    String ver = String.format("%02d.%02d.%02d", major, minor, revision);
    if (status == VersionStatus.ALPHA)
      ver += " alpha";
    if (status == VersionStatus.BETA)
      ver += " beta";
    if (0 != candidate)
      ver += String.format(" RC%02d", candidate);
    return ver;
  }

  
}
