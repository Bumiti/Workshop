package com.workshop.dto.DashBoardDTO;

import com.workshop.dto.CourseDTO.CourseResponses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DashboardDTO {
    private List<CourseResponses> weeklyCourseResponses;
    private List<CourseResponses> monthlyCourseResponses;
    private List<CourseResponses> yearlyCourseResponses;
}
