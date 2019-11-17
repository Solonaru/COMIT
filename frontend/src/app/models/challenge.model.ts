import { EnumObject } from './enum-object.model';
import { Event } from './event.model';

export interface Challenge {
    id: number;
    title: string;
    content: string;
    restrictions: string[];
    technologies: EnumObject[];
    skillLevel: EnumObject;
    event: Event;
    tags: string[];
}