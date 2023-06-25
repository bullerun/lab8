package common.utilities;
/**
 * class for setting the operating mode
 * @author Nikita and Vlad
 * @version 0.1
 */
public class RunMode {
    RunModeEnum runMode;
    public RunMode(){
        runMode= RunModeEnum.CONSOLE;
    }

    public RunModeEnum getRunMode() {
        return runMode;
    }

    public void setRunMode(RunModeEnum runMode) {
        this.runMode = runMode;
    }
}
