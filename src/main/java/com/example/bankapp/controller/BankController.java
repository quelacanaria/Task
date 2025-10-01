package com.example.bankapp.controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.bankapp.model.Account;
import com.example.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
// ...existing code...



@Controller
public class BankController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		var auth = SecurityContextHolder.getContext().getAuthentication();
    var roles = org.springframework.security.core.authority.AuthorityUtils.authorityListToSet(auth.getAuthorities());

    if (!roles.contains("ROLE_USER")) {
        // redirect normal users away
        return "redirect:/admin-dashboard";
    }
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Account account = accountService.findAccountByUsername(username);
		model.addAttribute("account",account);
		model.addAttribute("transactions", accountService.getTransactionHistory(account));
		return "dashboard";
	}

	@GetMapping("/admin-dashboard")
	public String adminDashboard(Model model2) {
		var auth = SecurityContextHolder.getContext().getAuthentication();
    var roles = org.springframework.security.core.authority.AuthorityUtils.authorityListToSet(auth.getAuthorities());

    if (!roles.contains("ROLE_ADMIN")) {
        // redirect normal users away
        return "redirect:/dashboard";
    }
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Account account = accountService.findAccountByUsername(username);
		model2.addAttribute("account",account);
		model2.addAttribute("accounts", accountService.getAllAccounts());

		return "admin-dashboard";
	}

	@GetMapping("/register")
	public String showRegistrationForm(){

	return "register";
	}

	@PostMapping("/register")
	public String registerAccount(@RequestParam String username, @RequestParam String password, Model model) {
		try {
			accountService.registerAccount(username, password);
			return "redirect:/login"; // on success go to login
		} catch(RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "register"; // return register view so template can show the error
		}
	}

	@GetMapping("/login")
	public String login() {
		var auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && auth.getAuthorities() != null && !auth.getAuthorities().isEmpty()) {
			var roles = org.springframework.security.core.authority.AuthorityUtils.authorityListToSet(auth.getAuthorities());
			if (roles.contains("ROLE_ADMIN")) return "redirect:/admin-dashboard";
			if (roles.contains("ROLE_USER")) return "redirect:/dashboard";
		}
		return "login";
	}


	@PostMapping("/deposit")
public String deposit(@RequestParam BigDecimal amount, Model model) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Account account = accountService.findAccountByUsername(username);

    accountService.deposit(account, amount);

    model.addAttribute("success", "Successfully deposited " + amount + " into your account.");
    model.addAttribute("account", account);
    model.addAttribute("transactions", accountService.getTransactionHistory(account));

    return "dashboard";
}

	

@PostMapping("/withdraw")
public String withdraw(@RequestParam BigDecimal amount,
                       RedirectAttributes redirectAttributes) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Account account = accountService.findAccountByUsername(username);

    try {
        accountService.withdraw(account, amount);
        redirectAttributes.addFlashAttribute("success", "Successfully withdrew " + amount + " from your account.");
    } catch (RuntimeException e) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/dashboard";
}


	@PostMapping("/dashboard")
public String transferAmount(@RequestParam String toUsername,
                             @RequestParam BigDecimal amount,
                             Model model) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Account fromAccount = accountService.findAccountByUsername(username);

    try {
        accountService.transferAmount(fromAccount, toUsername, amount);

        // ✅ Success works the same as error (with a message)
        model.addAttribute("success", "Transfer of " + amount + " to " + toUsername + " was successful.");

        model.addAttribute("account", fromAccount);
        model.addAttribute("transactions", accountService.getTransactionHistory(fromAccount));
        return "dashboard";

    } catch (RuntimeException e) {
        model.addAttribute("error", e.getMessage());
        model.addAttribute("account", fromAccount);
        model.addAttribute("transactions", accountService.getTransactionHistory(fromAccount));
        return "dashboard";
    }
}




		@PostMapping("/delete")
		public String deleteAccount(@RequestParam(required = false) Long id, @RequestParam(required = false) String username, Model model) {
			String current = SecurityContextHolder.getContext().getAuthentication().getName();
			try {
				if (username != null && !username.isBlank()) {
					if (username.equals(current)) {
						model.addAttribute("error", "Cannot delete currently logged-in account");
						return "redirect:/admin-dashboard";
					}
					Account acct = accountService.findAccountByUsername(username);
					if (acct != null && "ROLE_ADMIN".equals(acct.getRole())) {
						model.addAttribute("error", "Cannot delete account with ROLE_ADMIN");
						return "redirect:/admin-dashboard";
					}
					accountService.deleteAccountByUsername(username);
				} else if (id != null) {
					Account acct = accountService.findAccountById(id);
					if (acct.getUsername().equals(current)) {
						model.addAttribute("error", "Cannot delete currently logged-in account");
						return "redirect:/admin-dashboard";
					}
					if (acct != null && "ROLE_ADMIN".equals(acct.getRole())) {
						model.addAttribute("error", "Cannot delete account with ROLE_ADMIN");
						return "redirect:/admin-dashboard";
					}
					accountService.deleteAccountById(id);
				}
				return "redirect:/admin-dashboard";
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
				return "redirect:/admin-dashboard";
			}
		}

		// @PostMapping("/update")
		// public String updateAccount(@RequestParam Long id, @RequestParam String username){
		// 	// Implement update logic here if needed
		// 	return "redirect:/admin-dashboard";
		// }
@PostMapping("/update")
public String updateAccount(@RequestParam(required = false) Long id,
                            @RequestParam(required = false) String targetUsername,
                            @RequestParam String username,
                            @RequestParam(required = false) String password,
                            RedirectAttributes redirectAttributes) {
    
    String current = SecurityContextHolder.getContext().getAuthentication().getName();
    
    try {
        Account target = null;

        if (id != null) {
            target = accountService.findAccountById(id);
        } else if (targetUsername != null && !targetUsername.isBlank()) {
            target = accountService.findAccountByUsername(targetUsername);
        } else {
            redirectAttributes.addFlashAttribute("error", "No target specified");
            return "redirect:/admin-dashboard";
        }

        // prevent changing role here; only username/password
        if (target.getUsername().equals(current) && (password == null || password.isBlank())) {
            redirectAttributes.addFlashAttribute("error", "Provide a password when changing your own username");
            return "redirect:/admin-dashboard";
        }

        if (("ROLE_ADMIN".equals(target.getRole()) || "ROLE_USER".equals(target.getRole()))
                && !current.equals(target.getUsername())) {
            // optional restriction
        }

        accountService.updateAccountDetails(target.getId(), username, password);

        // ✅ Success message
        redirectAttributes.addFlashAttribute("success",
                "Account details for '" + target.getUsername() + "' were successfully updated.");

        return "redirect:/admin-dashboard";

    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/admin-dashboard";
    }
}




}
