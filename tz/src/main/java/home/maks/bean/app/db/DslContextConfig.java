package home.maks.bean.app.db;

import home.maks.config.Config;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderKeywordStyle;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DslContextConfig {
    @Autowired
    private Config config;

    @Bean
    public DSLContext dslContext() {
        Settings settings = new Settings()
                .withRenderNameStyle(RenderNameStyle.AS_IS)
                .withRenderKeywordStyle(RenderKeywordStyle.UPPER);
        if (config.isDbMemory()) {
            return DSL.using(SQLDialect.H2, settings);
        }
        return DSL.using(SQLDialect.POSTGRES, settings);
    }
}
