$(function () {
  var names = [
    "Tifany Kincade",
    "Pearly Dupler",
    "Margarita Abate",
    "Miriam So",
    "Desire Reichman",
    "Stefan Healey",
    "Quyen Steimle",
    "Deanne Proffitt",
    "Elouise Penta",
    "Kay Melchior",
    "Retta Miramontes",
    "Vince Lepine",
    "Tess Weingartner",
    "Diego Frechette",
    "Pierre Policastro",
    "Alphonso Yancey",
    "Agripina Gagliano",
    "Levi Pedersen",
    "Marty Krishnan",
    "Randa Augustus",
    "Merlin Parsley",
    "Glennie Hamrick",
    "Donald Newingham",
    "Barrett Gauntt",
    "Diana Schachter",
    "Delois Winborne",
    "Paulette Crumpler",
    "Dorla Yingling",
    "Edda Joplin",
    "Azucena Youngman",
    "Jeni Hagwood",
    "Marissa Wester",
    "Georgann Kilroy",
    "Evonne Horne",
    "Adriana Puls",
    "Patrice Woll",
    "Rebecka Ruddock",
    "Jackqueline Filippi",
    "Claude Kovar",
    "Darcie Montano",
    "Donny Ozuna",
    "Armando Plante",
    "Yuko Chronister",
    "Kassandra Mungia",
    "Rosario Corey",
    "Derek Peri",
    "Darwin Massie",
    "Almeda Binder",
    "Toby Barter",
    "Burl Edgemon"
  ];

  count = names.length;

  var setName = function() {

    $.ajax({
      url: 'http://rdc2019.cn/user/selectRand',
      type: 'get',
      dataType: 'json',
      // xhrFields: {
      //   withCredentials: true
      // },
      success: function (data) {
        // names = data.map(x=>x.contact);
        // console.log(names)
        // count = names.length;
        start();
      }
    })
  }();

  function start() {
    $('.abc').click(function(){
      selected = names[Math.random() * count | 0];
      covered = selected.replace(/[^\s]/g, '_');
      document.body.innerHTML = covered;
      timer = setInterval(decode, 50);
    })
  }

  function decode() {
    newtext = covered.split('').map(changeLetter()).join('');
    document.body.innerHTML = newtext;
    if (selected == covered) {
      clearTimeout(timer);
      // winnerRevealed();
      return false;
    }
    covered = newtext;
  }

  function changeLetter() {
    replacements = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz%!@&*#_ ';
    replacementsLen = replacements.length;
    return function (letter, index, err) {
      return selected[index] == letter
        ? letter
        : replacements[Math.random() * replacementsLen | 0];
    }
  }

  // function winnerRevealed(){
  //   alert('winner found');
  // }
})