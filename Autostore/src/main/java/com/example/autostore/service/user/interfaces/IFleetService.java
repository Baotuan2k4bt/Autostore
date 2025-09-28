package com.example.autostore.service.user.interfaces;

import com.example.autostore.dto.admin.CarLocationDTO;
import com.example.autostore.dto.admin.FleetCarDTO;
import com.example.autostore.dto.admin.FleetOverviewDTO;

import java.util.List;

public interface IFleetService {
    FleetOverviewDTO getOverview();
    List<FleetCarDTO> getAllCars();

    List<CarLocationDTO> getCarLocations();
    CarLocationDTO updateCarLocation(Integer carId, double latitude, double longitude);
}
