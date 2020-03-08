/*******************************************************************************
 * Copyright 2020 riotjy and listed authors
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
 *    
 *    Authors:
 *      Alex Savulov
 *******************************************************************************/
package dev.riotjy.mobjy.util;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobjyConfig {

  final static Logger log = LoggerFactory.getLogger(MobjyConfig.class);
  
  static private final MobjyConfig mobjyConfig = new MobjyConfig();
  
  Options cliOpts;
  CommandLine cmdLine;
  ConcurrentHashMap<String, String> configMap;
  
  static public MobjyConfig getInst() {
    return mobjyConfig;
  }
  
  private MobjyConfig() {
    configMap = new ConcurrentHashMap<>();
    
    cliOpts = new Options();
    
    cliOpts.addOption(StrConst.CLI_IN_FILE_NAME.toString(), true, "Input file path and name.");
    cliOpts.addOption(StrConst.CLI_OUT_PATH.toString(), true, "Root of the output path");
    cliOpts.addOption(StrConst.CLI_PACKAGE_NAME.toString(), true, "Package name.");
    cliOpts.addOption(StrConst.CLI_HELP.toString(), false, "Print the help and exit.");
  }
  
  public boolean parseCommandLine(String[] args) {
    CommandLineParser parser = new DefaultParser();
    try {
        cmdLine = parser.parse(cliOpts, args);
    } catch (ParseException e) {
        log.error("Command line arguments parsing exception: \"" + e.getMessage() + "\" Using defaults.");
        return false;
    }
    return true;
  }
  
  public boolean hasOption(StrConst opt) {
    return cmdLine.hasOption(opt.toString());
  }

  public Integer getCmdInt(StrConst opt) {
    return getInst().getInt(opt);
  }

  public String getCmdStr(StrConst opt) {
    return getInst().getStr(opt);
  }

  public Boolean getCmdBool(StrConst opt) {
    return getInst().getBool(opt);
  }
  
  public void printFormatedHelp() {
    HelpFormatter fmtr = new HelpFormatter();
    System.out.println("Welcome to mobjy for Java!\n");
    fmtr.printHelp("Mobjy", cliOpts);
  }
  
  private Integer getInt(StrConst opt) {
    try {
        if (cmdLine.hasOption(opt.toString()))
            return Integer.parseInt(cmdLine.getOptionValue(opt.toString()));
    } catch (NumberFormatException e) {
        log.error("Format exception: entry <" + opt + "> is not an integer!");
        return null;
    }
    log.error("Config entry <" + opt + "> not found");
    return null;
  }
  
  private Boolean getBool(StrConst opt) {
      try {
        if (cmdLine.hasOption(opt.toString()))
          return Boolean.parseBoolean(cmdLine.getOptionValue(opt.toString()));
      } catch (NumberFormatException e) {
          log.error("Exception while parsing optd configuration entry <" + opt + ">: Not a boolean!");
          return null;
      }
      log.error("Config entry <" + opt + "> not found");
      return null;
  }
  
  private String getStr(StrConst opt) {
      if (cmdLine.hasOption(opt.toString()))
          return cmdLine.getOptionValue(opt.toString());
      log.error("Config entry <" + opt + "> not found");
      return null;
  }

  private Integer getInt(String name) {
      try {
          if (configMap.containsKey(name))
              return Integer.parseInt(configMap.get(name));
      } catch (NumberFormatException e) {
          log.error("Format exception: entry <" + name + "> is not an integer!");
          return null;
      }
      log.error("Config entry <" + name + "> not found");
      return null;
  }
  
  private Boolean getBool(String name) {
      try {
        if (configMap.containsKey(name))
          return Boolean.parseBoolean(configMap.get(name));
      } catch (NumberFormatException e) {
          log.error("Exception while parsing named configuration entry <" + name + ">: Not a boolean!");
          return null;
      }
      log.error("Config entry <" + name + "> not found");
      return null;
  }
  
  private String getStr(String name) {
      if (configMap.containsKey(name))
          return configMap.get(name);
      log.error("Config entry <" + name + "> not found");
      return null;
  }

}
