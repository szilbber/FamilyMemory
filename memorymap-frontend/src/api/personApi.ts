
import type { Person } from '../types/Person'
import type { PersonPhoto } from '../types/PersonPhoto'
import type { BurialLocation } from '../types/BurialLocation'

const API_URL = 'http://localhost:8080/persons'

export async function getPersons(): Promise<Person[]> {
    const response = await fetch(API_URL)

    if (!response.ok) {
        throw new Error('Не удалось загрузить список людей')
    }

    return response.json() as Promise<Person[]>
}

export async function getPerson(id: string): Promise<Person> {
    const response = await fetch(`${API_URL}/${id}`)

    if (!response.ok) {
        throw new Error('Не удалось загрузить информацию о человеке')
    }

    return response.json() as Promise<Person>
}

export async function getPersonPhotos(
    id: string
): Promise<PersonPhoto[]> {
    const response = await fetch(`${API_URL}/${id}/photos`)

    if (!response.ok) {
        throw new Error('Не удалось загрузить фотографии человека')
    }

    return response.json() as Promise<PersonPhoto[]>
}

export async function getBurialLocation(id: string): Promise<BurialLocation | null> {
    const response = await fetch(`${API_URL}/${id}/burial-location`)

    if (response.status === 404 || response.status === 204) {
        return null
    }

    if (!response.ok) {
        throw new Error('Не удалось загрузить место захоронения')
    }

    const text = await response.text()

    if (!text.trim() || text.trim() === 'null') {
        return null
    }

    return JSON.parse(text) as BurialLocation
}

