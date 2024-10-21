$(document).ready(function() {

    $('#mobile_btn').on('click', function () {
        $('#mobile_menu').toggleClass('active');
        $('#mobile_btn').find('i').toggleClass('fa-x');
    });

        const sections = $('section');
        const navItems = $('.nav-item');
        
        $(window).on('scroll', function () {
            const header = $('header');
            const scrollPosition = $(window).scrollTop() - header.outerHeight();
        
            let activeSectionIndex = 0;
        
            if (scrollPosition <= 0) {
                header.css('box-shadow', 'none');
            } else {
                header.css('box-shadow', '5px 1px 5px rgba(0, 0, 0, 0.1');
            }
        
            sections.each(function(i) {
                const section = $(this);
                const sectionTop = section.offset().top - 96;
                const sectionBottom = sectionTop+ section.outerHeight();
        
                if (scrollPosition >= sectionTop && scrollPosition < sectionBottom) {
                    activeSectionIndex = i;
                    return false;
                }
            })
        
            navItems.removeClass('active');
            $(navItems[activeSectionIndex]).addClass('active');
        });
        
        
        
        ScrollReveal().reveal('#navbar', {
                duration: 2000,
            });
        ScrollReveal().reveal('#cta', {
            origin: 'top',
            duration: 2000,
            distance: '20%'
        });
        
        ScrollReveal().reveal('#cta-button', {
            origin: 'top',
            duration: 2000,
            distance: '20%'
        });
        
        ScrollReveal().reveal('.book', {
            origin: 'right',
            duration: 2000,
            distance: '20%'
        });
        
        ScrollReveal().reveal('.rating-image', {
            origin: 'left',
            duration: 2000,
            distance: '20%'
        });
        
        ScrollReveal().reveal('.review', {
            origin: 'right',
            duration: 2000,
            distance: '20%'
        });

        ScrollReveal().reveal('main #reviews',{
            duration:2000,
            origin: 'right',
            distance: '10%'
        })
        ScrollReveal().reveal('#book-details',{
            duration:2000,
        })
        ScrollReveal().reveal('#author-details',{
            duration:2000,
        })
        ScrollReveal().reveal('#book-container',{
            duration:2000,
            origin: 'right',
            distance: '10%'
        })

    });

function goBack() {
    window.history.back();
  }

const arrows = document.querySelectorAll(".arrow");
const movieLists = document.querySelectorAll(".book-list");

arrows.forEach((arrow, i) => {
  const itemNumber = movieLists[i].querySelectorAll("img").length;
  let clickCounter = 0;
  arrow.addEventListener("click", () => {
    const ratio = Math.floor(window.innerWidth / 270);
    clickCounter++;
    if (itemNumber - (4 + clickCounter) + (5 - ratio) >= 0) {
      movieLists[i].style.transform = `translateX(${
        movieLists[i].computedStyleMap().get("transform")[0].x.value - 320
      }px)`;
    } else {
      movieLists[i].style.transform = "translateX(0)";
      clickCounter = 0;
    }
  });
});