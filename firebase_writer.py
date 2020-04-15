

from firebase import firebase


fb = firebase.FirebaseApplication(
    'https://test-login-3ce73.firebaseio.com', None)


# print(fb.get('/content', ''))

payload = {
    'Insomnia': {
        'description': 'Persistent problems falling and staying asleep.',
        'symptoms': ['Difficulty falling asleep at night, Waking up during the night',
                     'Daytime tiredness or sleepiness',
                     'Difficulty paying attention, focusing on tasks or remembering'
                     ],
        'treatment': [
            'Light therapy and Cognitive behavioral therapy',
            'Improving sleep habits',
            'Sedative and antidepressants'
        ]

    },

    'Hypochondria': {
        'description': 'Obsession with the idea of having a serious but undiagnosed medical condition.',
        'symptoms': ['Being preoccupied with having or getting a serious disease or health condition',
                     'Worrying that minor symptoms or body sensations mean you have a serious illness',
                     'Being easily alarmed about your health status'],
        'treatment': ['Therapies and Stress management']

    },
    'Depression': {
        'description': 'A mental health disorder characterised by persistently depressed mood or loss of interest in activities, causing significant impairment in daily life.',
        'symptoms': ['Anxiety, apathy, general discontent',
                     'Agitation, excessive crying, irritability, restlessness',
                     'Thoughts of suicide.'],
        'treatment': [
            'Antidepressants',
            'Cognitive behavioral therapy',
            'Psychotherapy']
    },
    'bp': {
        'description': '',
        'symptoms': [
            'symptom 1',
            'symptom 1'
        ],
        'treatment': [
            'treatment 1',
            'treatment 2'
        ]
    }
}


result = fb.put('/content', 'diagnosis', payload)
