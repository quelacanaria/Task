    document.addEventListener("DOMContentLoaded", function () {
        const modalEl = document.getElementById('DashboardState');
        if (modalEl) {
            const modal = new bootstrap.Modal(modalEl);
            modal.show();
        }
    });
    document.addEventListener("DOMContentLoaded", function () {
        const modalEl = document.getElementById('DashboardStatesuccess');
        if (modalEl) {
            const modal = new bootstrap.Modal(modalEl);
            modal.show();
        }
    });