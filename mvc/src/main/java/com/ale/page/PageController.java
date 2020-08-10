package com.ale.page;

import com.ale.common.Page;
import com.ale.dto.UserQuery;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分页
 *
 * @author alewu
 * @date 2020/5/18
 */
@RestController
@AllArgsConstructor
public class PageController {
    private final PageService pageService;

    /**
     * @author alewu
     * @date 2020/5/18 9:54
     */
    @GetMapping("/page")
    public R<Page<User>> pageXXX(Page<User> page, @RequestBody UserQuery query) {
        Page<User> pageResult = pageService.pageXXX(page, query);
        return R.ok(pageResult);
    }
}
