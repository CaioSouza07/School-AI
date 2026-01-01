const menu = document.querySelector('.menu-lateral');
const btnFechar = document.getElementById('fechar-menu');

btnFechar.addEventListener('click', (e) => {
    e.stopPropagation(); // evita conflito com clique no menu
    menu.classList.toggle('fechado');
});

menu.addEventListener('click', () => {
if (menu.classList.contains('fechado')) {
        menu.classList.remove('fechado');
    }
});