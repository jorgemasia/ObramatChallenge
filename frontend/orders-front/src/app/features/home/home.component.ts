import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  template: `<h1>Home</h1> jroña que jroña`
})
export class HomeComponent {
ngOnInit(): void {
  console.log('HomeComponent cargado');
}
}