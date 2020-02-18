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
package dev.riotjy.mobjy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.riotjy.mobjy.builder.ModelBuilder;
import dev.riotjy.mobjy.export.DeserializeExporter;
import dev.riotjy.mobjy.export.ModelExporter;
import dev.riotjy.mobjy.export.SerializeExporter;
import dev.riotjy.mobjy.model.MjyModel;
import dev.riotjy.mobjy.util.MobjyConfig;
import dev.riotjy.mobjy.util.StrConst;

public class Mobjy {

  final static Logger log = LoggerFactory.getLogger(Mobjy.class);

  public static void main(String[] args) throws Exception {
    
    MobjyConfig cfg = MobjyConfig.getInst();
    
    cfg.parseCommandLine(args);
    
    if (cfg.hasOption(StrConst.CLI_HELP)) {
      cfg.printFormatedHelp();
      return;
    }
    
    String inFileName = "";
    if (cfg.hasOption(StrConst.CLI_IN_FILE_NAME)) {
      inFileName = cfg.getCmdStr(StrConst.CLI_IN_FILE_NAME);
    } else {
      log.error("Missing input file argument");
      return;
    }
    
    ModelBuilder mBuilder = new ModelBuilder(inFileName);
    
    mBuilder.dumpLoaderInfo();
    
    MjyModel theModel = mBuilder.build();
    
    ModelExporter exporter = new ModelExporter(theModel);
    
    exporter.exportJava("");
    
    SerializeExporter serexp = new SerializeExporter(theModel);
    
    serexp.exportJava("");
    
    DeserializeExporter deserexp = new DeserializeExporter(theModel);
    
    deserexp.exportJava("");

    exporter.exportCpp("");

    serexp.exportCpp("");
    
    log.info(theModel.toString());
  }
}
