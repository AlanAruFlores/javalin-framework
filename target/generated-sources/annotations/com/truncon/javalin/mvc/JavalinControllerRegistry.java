package com.truncon.javalin.mvc;

import com.ar.javalin.base.controller.ItemController;
import com.truncon.javalin.mvc.api.ActionResult;
import com.truncon.javalin.mvc.api.HttpContext;
import io.javalin.Javalin;
import io.javalin.http.Context;
import jakarta.annotation.Generated;
import java.lang.Exception;
import java.lang.Override;

@Generated("com.truncon.javalin.mvc.annotations.processing.ControllerProcessor")
public final class JavalinControllerRegistry implements ControllerRegistry {
    public JavalinControllerRegistry() {
    }

    private void httpHandler0(Context ctx) throws Exception {
        HttpContext wrapper = new JavalinHttpContext(ctx);
        ItemController controller = new ItemController();
        ActionResult result = controller.index();
        result.execute(wrapper);
    }

    @Override
    public void register(Javalin app) {
        app.get("/index", this::httpHandler0);
    }
}
