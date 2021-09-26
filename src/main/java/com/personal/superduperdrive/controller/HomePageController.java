package com.personal.superduperdrive.controller;

import com.personal.superduperdrive.services.CredentialService;
import com.personal.superduperdrive.services.FileService;
import com.personal.superduperdrive.services.NoteService;
import com.personal.superduperdrive.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/home")
public class HomePageController {

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private FileService fileService;

    @Autowired
    private NoteService noteService;


    @GetMapping
    public String getHome(Model model, Authentication authentication) {
        userService.loggedinuser = userService.getUser(authentication.getName());

        model.addAttribute("credentialList", credentialService.getCredentialbyUserId(userService.loggedinuser.getUserId()));
        model.addAttribute("fileList", fileService.getFileByUser(userService.loggedinuser.getUserId()));
        model.addAttribute("noteList", noteService.getNotes(userService.loggedinuser.getUserId()));

        return "home";
    }
}
