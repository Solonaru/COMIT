import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EnumObjectService } from 'src/app/services/enum-object.service';

@Component({
  selector: 'app-challenges',
  templateUrl: './challenges.component.html',
  styleUrls: ['./challenges.component.css']
})
export class ChallengesComponent implements OnInit {
  eventTypes: string[];
  technologies: string[];
  skillLevels: string[];

  constructor(
    private router: Router,
    private enumObjectService: EnumObjectService) { }

  ngOnInit() {
    this.enumObjectService.getEventTypes().subscribe(data => {
      this.eventTypes = data;
    });

    this.enumObjectService.getTechnologies().subscribe(data => {
      this.technologies = data;
    });

    this.enumObjectService.getSkillLevels().subscribe(data => {
      this.skillLevels = data;
    })
  }

  goToPage () {
    this.router.navigate(['/challenge', '1'])
  }
}
