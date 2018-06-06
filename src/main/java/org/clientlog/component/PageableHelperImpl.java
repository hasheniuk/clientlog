package org.clientlog.component;

import org.clientlog.dto.PageRequestDTO;
import org.clientlog.exception.InvalidPageRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PageableHelperImpl implements PageableHelper {

    @Override
    public Pageable asPageable(PageRequestDTO dto) {
        Integer page = dto.getPage();
        Integer count = dto.getCount();
        if (page != null && page < 0 || count != null && count < 1) {
            throw new InvalidPageRequestException();
        }
        if (count == null) return Pageable.unpaged();
        if (page == null) return PageRequest.of(0, count);
        return PageRequest.of(page, count);
    }
}
