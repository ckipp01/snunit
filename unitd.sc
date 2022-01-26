import java.util.concurrent.atomic.AtomicBoolean

// Variables are not maintained among invocations in interactive (`-i`) mode.
// Running Mill with `-i` in TestUtils breaks makes it impossible to kill the previous processes.
private var optProc: Option[os.SubProcess] = None
private def closeUnitd(): Unit = {
  optProc.foreach { proc =>
    proc.close()
    // Wait for Unit to close itself gracefully
    Thread.sleep(100)
    optProc = None
  }
}
def runBackground(config: ujson.Obj): Unit = {
  closeUnitd()
  val dest = os.home / ".cache" / "snunit"
  val state = dest / "state"
  os.makeDir.all(state)
  os.write.over(state / "conf.json", config)
  val control = dest / "control.sock"
  os.remove(control)
  val started = new AtomicBoolean(false)
  optProc = Some(
    os.proc(
      "unitd",
      "--no-daemon",
      "--log",
      "/dev/stdout",
      "--state",
      state,
      "--control",
      s"unix:$control",
      "--pid",
      dest / "unit.pid"
    ).spawn(stderr = os.ProcessOutput.Readlines(line => {
      line match {
        case s"$_ unit $_ started" =>
          started.set(true)
        case _ =>
      }
      System.err.println(line)
    }))
  )
  while (!started.get()) {
    println("Waiting for unit to start...")
    Thread.sleep(100)
  }
}
