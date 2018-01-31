package ruolan.com.messagecenter.injection.module

import dagger.Module
import dagger.Provides
import ruolan.com.messagecenter.service.MessageService

/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function
 */

@Module
class MessageModule {

    @Provides
    fun providerMessageService(messageService: MessageService):MessageService{
        return messageService
    }

}