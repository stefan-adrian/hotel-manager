package fii.hotel.manager.dto;

import java.io.Serializable;
import java.util.List;

public class AllRoomservicesDto implements Serializable {

    private List<RoomserviceDto> activeRoomservice;
    private List<RoomserviceDto> inactiveRoomservice;

    public AllRoomservicesDto() {
    }

    public AllRoomservicesDto(List<RoomserviceDto> activeRoomservice, List<RoomserviceDto> inactiveRoomservice) {
        this.activeRoomservice = activeRoomservice;
        this.inactiveRoomservice = inactiveRoomservice;
    }

    public List<RoomserviceDto> getActiveRoomservice() {
        return activeRoomservice;
    }

    public void setActiveRoomservice(List<RoomserviceDto> activeRoomservice) {
        this.activeRoomservice = activeRoomservice;
    }

    public List<RoomserviceDto> getInactiveRoomservice() {
        return inactiveRoomservice;
    }

    public void setInactiveRoomservice(List<RoomserviceDto> inactiveRoomservice) {
        this.inactiveRoomservice = inactiveRoomservice;
    }
}
