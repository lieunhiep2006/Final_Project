document.addEventListener("click", function (event) {
    const toggle = event.target.closest(".password-visibility-toggle");
    if (!toggle) {
        return;
    }

    const passwordInput = document.getElementById(toggle.getAttribute("aria-controls"));
    if (!passwordInput) {
        return;
    }

    const showPassword = passwordInput.type === "password";
    passwordInput.type = showPassword ? "text" : "password";
    toggle.setAttribute("aria-pressed", String(showPassword));
    toggle.setAttribute("aria-label", showPassword ? "Ẩn mật khẩu" : "Hiển thị mật khẩu");
    toggle.innerHTML = '<i class="fa-solid ' + (showPassword ? "fa-eye-slash" : "fa-eye") + '" aria-hidden="true"></i>';
});