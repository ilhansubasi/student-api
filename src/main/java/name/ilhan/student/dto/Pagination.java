package name.ilhan.student.dto;

import lombok.Data;

@Data
public class Pagination {
    private Integer pageNo = 0;
    private Integer pageSize = 10;
}
