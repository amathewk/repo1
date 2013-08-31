import grails.util.Environment
import net.followup.DebugController

class BootStrap {

    def init = { servletContext ->
        switch (Environment.current) {
            case Environment.DEVELOPMENT :
                DebugController.loadPatients()
                break;
        }
    }
    def destroy = {
    }

}
