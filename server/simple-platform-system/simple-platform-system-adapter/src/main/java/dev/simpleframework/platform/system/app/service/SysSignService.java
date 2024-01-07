package dev.simpleframework.platform.system.app.service;

import dev.simpleframework.platform.system.api.SysSignApi;
import dev.simpleframework.platform.system.app.executor.SignInExecutor;
import dev.simpleframework.platform.system.app.executor.SignOutExecutor;
import dev.simpleframework.platform.system.app.executor.SignUpExecutor;
import dev.simpleframework.platform.system.model.SignInArgs;
import dev.simpleframework.platform.system.model.SignUpArgs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Service
public class SysSignService implements SysSignApi {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void up(SignUpArgs args) {
        new SignUpExecutor(args).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String in(SignInArgs args) {
        return new SignInExecutor(args).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void out() {
        new SignOutExecutor().exec();
    }

}
