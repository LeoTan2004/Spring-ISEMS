package com.csb.dto;

import com.csb.module.team.TeamStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TeamCreateParam {
    private Long admin;
    private String teamName;
    private String description;
}
