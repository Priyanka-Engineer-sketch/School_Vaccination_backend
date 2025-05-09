package com.schoolvaccination.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDTO {
    private long totalStudents;
    private long vaccinatedStudents;
    private double vaccinatedPercentage;
    private List<UpcomingDriveDTO> upcomingDrives;

    // getters and setters
}