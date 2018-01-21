package ruolan.com.baselibrary.injection

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class PerComponentScope