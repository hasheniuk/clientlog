package org.clientlog.component;

import org.clientlog.dto.PageRequestDTO;
import org.springframework.data.domain.Pageable;

public interface PageableHelper {
    Pageable asPageable(PageRequestDTO dto);
}
