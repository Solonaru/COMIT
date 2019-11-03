import { Directive, HostListener, HostBinding } from '@angular/core';

@Directive({
    selector: '[appMenuOpen]'
})
export class MobileMenuDirective {
    constructor() { }

    @HostBinding('class.test') isOpen = false;

    @HostListener('click') toggleOpen() {
        this.isOpen = !this.isOpen;
        console.log('saf')
    }
}