
    document.addEventListener("DOMContentLoaded", function () {
        const modalEl = document.getElementById('staticBackdropForTransferMoney');
        if (modalEl) {
            const modal = new bootstrap.Modal(modalEl);
            modal.show();
        }
    });
