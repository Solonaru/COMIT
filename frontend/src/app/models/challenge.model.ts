import { EnumObject } from './enum-object.model';
import { Event } from './event.model';
import { Solution } from './solution.model';
import { Resource } from './resource.model';

export class Challenge {
    id: number;
    title: string;
    content: string;
    restrictions: string[];
    tips: string[];
    technologies: EnumObject[];
    skillLevel: EnumObject;
    event: Event;
    solutions: Solution[];
    resources: Resource[];
    tags: string[];
}