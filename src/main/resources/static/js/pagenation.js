$(function () {
    $('.showItemContainer').paginathing({//親要素のclassを記述
        perPage: 10,//1ページあたりの表示件数
        prevText: '前へ',//1つ前のページへ移動するボタンのテキスト
        nextText: '次へ',//1つ次のページへ移動するボタンのテキスト
        firstText: '<i class="fas fa-angle-double-left"></i>',
        lastText: '<i class="fas fa-angle-double-right"></i>',
        prevText: '<i class="fas fa-angle-left"></i>',
        nextText: '<i class="fas fa-angle-right"></i>',
        activeClass: 'active',
    })
});

$(function () {
    $('.reviewAll').paginathing({//親要素のclassを記述
        perPage: 20,//1ページあたりの表示件数
        prevText: '前へ',//1つ前のページへ移動するボタンのテキスト
        nextText: '次へ',//1つ次のページへ移動するボタンのテキスト
        firstText: '<i class="fas fa-angle-double-left"></i>',
        lastText: '<i class="fas fa-angle-double-right"></i>',
        prevText: '<i class="fas fa-angle-left"></i>',
        nextText: '<i class="fas fa-angle-right"></i>',
        activeClass: 'active',
    })
});

