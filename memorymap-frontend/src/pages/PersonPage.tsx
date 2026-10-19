
import { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import {
    getPerson,
    getPersonPhotos,
    getBurialLocation,
} from '../api/personApi'
import type { Person } from '../types/Person'
import type { PersonPhoto } from '../types/PersonPhoto'
import type { BurialLocation } from '../types/BurialLocation'
import '../App.css'

function PersonPage() {
    const { id } = useParams<{ id: string }>()

    const [person, setPerson] = useState<Person | null>(null)
    const [photos, setPhotos] = useState<PersonPhoto[]>([])
    const [burialLocation, setBurialLocation] =
        useState<BurialLocation | null>(null)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        if (!id) {
            setError('Не указан ID человека')
            setLoading(false)
            return
        }

        setLoading(true)
        setError(null)

        Promise.all([
            getPerson(id),
            getPersonPhotos(id),
            getBurialLocation(id),
        ])
            .then(([personData, photoData, locationData]) => {
                setPerson(personData)
                setPhotos(photoData)
                setBurialLocation(locationData)
            })
            .catch((err: unknown) => {
                setError(
                    err instanceof Error
                        ? err.message
                        : 'Не удалось загрузить данные'
                )
            })
            .finally(() => setLoading(false))
    }, [id])

    if (loading) {
        return (
            <div className="person-page">
                <p>Загружаем информацию о человеке...</p>
            </div>
        )
    }

    if (error || !person) {
        return (
            <div className="person-page">
                <p>{error ?? 'Человек не найден'}</p>
                <Link to="/">← Вернуться на главную</Link>
            </div>
        )
    }

    const fullName = [
        person.firstName,
        person.middleName,
        person.lastName,
    ]
        .filter(Boolean)
        .join(' ') || 'Имя не указано'

    const birth = person.birthDate
        ? person.birthDate.slice(0, 4)
        : person.birthYear

    const death = person.deathDate
        ? person.deathDate.slice(0, 4)
        : person.deathYear

    const years = `${birth ?? '—'} — ${death ?? 'н. в.'}`

    const initials = (
        (person.firstName?.[0] ?? '') +
        (person.lastName?.[0] ?? '')
    ).toUpperCase() || '?'

    return (
        <div className="app-shell">
            <header className="topbar">
                <Link className="brand" to="/" aria-label="MemoryMap — главная">
                    <span className="brand-mark">m</span>
                    <span>memory<span className="brand-light">map</span></span>
                </Link>

                <Link className="header-action" to="/">
                    ← Назад к архиву
                </Link>
            </header>

            <main className="person-page">
                <Link className="person-back-link" to="/">
                    ← Все люди
                </Link>

                <section className="person-detail-card">
                    <div className="person-detail-heading">
                        <div className="person-detail-avatar">
                            {initials}
                        </div>

                        <div>
                            <span className="section-kicker">
                                СЕМЕЙНЫЙ АРХИВ
                            </span>
                            <h1>{fullName}</h1>
                            <p className="person-detail-years">{years}</p>
                        </div>
                    </div>

                    <div className="person-detail-divider" />

                    <section className="person-detail-section">
                        <h2>История жизни</h2>

                        {person.biography?.trim() ? (
                            <p className="person-biography">
                                {person.biography}
                            </p>
                        ) : (
                            <p className="person-empty">
                                История этого человека пока не добавлена.
                            </p>
                        )}
                    </section>

                    <div className="person-detail-divider" />

                    <section className="person-detail-section">
                        <h2>Фотографии</h2>

                        {photos.length > 0 ? (
                            <div className="person-photos-grid">
                                {photos.map((photo) => (
                                    <article
                                        className="person-photo-card"
                                        key={photo.id}
                                    >
                                        {photo.fileUrl ? (
                                            <img
                                                src={photo.fileUrl}
                                                alt={photo.title || fullName}
                                                className="person-photo-image"
                                            />
                                        ) : (
                                            <div className="person-photo-placeholder">
                                                Фото пока недоступно
                                            </div>
                                        )}

                                        {photo.title && (
                                            <p>{photo.title}</p>
                                        )}
                                    </article>
                                ))}
                            </div>
                        ) : (
                            <p className="person-empty">
                                Фотографии пока не добавлены.
                            </p>
                        )}
                    </section>

                    <div className="person-detail-divider" />

                    <section className="person-detail-section">
                        <h2>Место захоронения</h2>

                        {burialLocation ? (
                            <div className="burial-location-card">
                                {burialLocation.description && (
                                    <p className="person-biography">
                                        {burialLocation.description}
                                    </p>
                                )}

                                <p>
                                    <strong>Широта:</strong>{' '}
                                    {burialLocation.latitude}
                                </p>
                                <p>
                                    <strong>Долгота:</strong>{' '}
                                    {burialLocation.longitude}
                                </p>
                                <p>
                                    <strong>Точность местоположения:</strong>{' '}
                                    {burialLocation.isExact
                                        ? 'Точное'
                                        : 'Приблизительное'}
                                </p>

                                <a
                                    className="text-link"
                                    href={`https://www.openstreetmap.org/?mlat=${burialLocation.latitude}&mlon=${burialLocation.longitude}#map=16/${burialLocation.latitude}/${burialLocation.longitude}`}
                                    target="_blank"
                                    rel="noreferrer"
                                >
                                    Посмотреть на карте ↗
                                </a>
                            </div>
                        ) : (
                            <p className="person-empty">
                                Место захоронения пока не указано.
                            </p>
                        )}
                    </section>
                </section>

                <footer className="footer">
                    <Link className="brand footer-brand" to="/">
                        <span className="brand-mark">m</span>
                        <span>memory<span className="brand-light">map</span></span>
                    </Link>
                    <p>Создано с заботой о памяти и семье.</p>
                    <span className="footer-year">© 2026 MemoryMap</span>
                </footer>
            </main>
        </div>
    )
}

export default PersonPage
