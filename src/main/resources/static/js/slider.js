const swiper = new Swiper('.sample-slider', {
    loop: true,                         //ループ
    autoplay: {                         //自動再生
        delay: 5000,
    },
    pagination: {                       //ページネーション（ドット）
        el: '.swiper-pagination',
    },
    navigation: {                       //ナビゲーション（矢印）
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
})