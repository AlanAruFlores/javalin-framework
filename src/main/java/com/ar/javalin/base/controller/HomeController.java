package com.ar.javalin.base.controller;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.ar.javalin.base.utils.PathUtilsConstants;
import com.truncon.javalin.mvc.api.ActionResult;
import com.truncon.javalin.mvc.api.Controller;
import com.truncon.javalin.mvc.api.HttpGet;
import com.truncon.javalin.mvc.api.StreamResult;

@Controller
public class HomeController {

    @HttpGet(route = "/")
    public ActionResult getLoginPage() throws IOException {
        InputStream html = Files.newInputStream(Paths.get(PathUtilsConstants.WEB_RESOURCE_PATH+"index.html"));
        return new StreamResult(html, "text/html");
    }
}
