package com.elementanalytics.umbrella

import com.elementanalytics.datasets.iceberg.IcebergLogic
import com.elementanalytics.datasets.server.DatasetsLogic
import com.elementanalytics.datasetslabeling.server.LabelingLogic
import com.elementanalytics.job.api.Scheduler
import com.elementanalytics.piagent.api.PiAgent

object Runner extends App {

  DatasetsLogic.run(false, false)
  LabelingLogic.run(false, false)
  IcebergLogic.run(migrate = false, processEvents = false)

  Scheduler.run(false)
  PiAgent.run
}

object LocalRunner extends App {

  DatasetsLogic.run(true, true)
  LabelingLogic.run(true, true)
  IcebergLogic.run(migrate = true, processEvents = true)

  Scheduler.run(true)
  PiAgent.run
}
