package com.elementanalytics.umbrella

import com.elementanalytics.datasets.iceberg.IcebergLogic
import com.elementanalytics.datasets.server.DatasetsLogic
import com.elementanalytics.datasetslabeling.server.LabelingLogic
import com.elementanalytics.job.api.Scheduler
import com.elementanalytics.piagent.api.PiAgent

object Runner extends App {

//  DatasetsLogic.run
  IcebergLogic.run(false)
  LabelingLogic.run()
//  Scheduler.run
//  PiAgent.run
  //WorkbenchService.run()

}
