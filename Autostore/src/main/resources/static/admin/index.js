document.addEventListener("DOMContentLoaded", () => {
    // === SIDEBAR INDICATOR ===
    const menuItems = document.querySelectorAll(".sidebar-menu ul li a");
    const indicator = document.querySelector(".sidebar-indicator");

    function updateIndicator(el) {
        const sidebarTop = document.querySelector(".sidebar-menu").getBoundingClientRect().top;
        const elTop = el.getBoundingClientRect().top;
        indicator.style.top = `${elTop - sidebarTop}px`;
    }

    const activeItem = document.querySelector(".sidebar-menu ul li a.active");
    if (activeItem) updateIndicator(activeItem);

    menuItems.forEach(item => {
        item.addEventListener("click", () => {
            menuItems.forEach(i => i.classList.remove("active"));
            item.classList.add("active");
            updateIndicator(item);
        });
    });

    // === SIDEBAR TOGGLE ===
    const toggleBtn = document.getElementById("toggle-btn");
    const sidebar = document.querySelector(".sidebar");
    const mainContent = document.querySelector(".main-content");
    const header = document.querySelector("header");

    toggleBtn.addEventListener("click", () => {
        sidebar.classList.toggle("collapsed");
        mainContent.classList.toggle("collapsed");
        header.classList.toggle("collapsed");
    });

    // === SUBMENU TOGGLE (WITH MEMORY) ===
    const submenuParents = document.querySelectorAll(".has-submenu");
    const openMenus = JSON.parse(localStorage.getItem("openSubmenus")) || [];

    submenuParents.forEach((parent, index) => {
        const trigger = parent.querySelector("a");

        if (openMenus.includes(index)) {
            parent.classList.add("open");
        }

        trigger.addEventListener("click", (e) => {
            // Prevent link from triggering page change if it has submenu
            if (parent.querySelector(".submenu")) {
                e.preventDefault();
            }

            parent.classList.toggle("open");

            const updated = Array.from(submenuParents).reduce((acc, item, i) => {
                if (item.classList.contains("open")) acc.push(i);
                return acc;
            }, []);

            localStorage.setItem("openSubmenus", JSON.stringify(updated));
        });
    });

});
